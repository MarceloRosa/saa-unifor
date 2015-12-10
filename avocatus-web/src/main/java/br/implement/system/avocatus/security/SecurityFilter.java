/**
 * 
 */
package br.implement.system.avocatus.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.implement.system.avocatus.entity.Papel;
import br.implement.system.avocatus.entity.Usuario;
import br.implement.system.avocatus.to.SegurancaTO;

/**
 * @author adrianopatrick@gmail.com
 * @since 5 de nov de 2015
 */
public class SecurityFilter implements Filter {

	private FilterConfig filterConfig;
	private static final String[] EXCLUDE_PAGES = { "/index.html",
			"/login.jsf", "/cadUsuario.jsf", "/accessDenied.jsf" };
	private static final String LOGIN_PAGE = "/pages/login/login.jsf";
	private static final String ACCESS_DENIED = "/pages/login/accessDenied.jsf";
	private static final Logger logger = Logger.getLogger(SecurityFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		SegurancaTO segurancaTO = (SegurancaTO) WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext())
				.getBean("segurancaTO");
		String url = ((HttpServletRequest) request).getServletPath();
		
		if (!(isExcludeURL(url) || (segurancaTO.isAutenticado() && possuiPermissao(url, segurancaTO.getUsuario())))) {
			logger.info(
					"Usuário não autenticado. Enviando para página de login ");
			segurancaTO.setUsuario(null);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(
					filterConfig.getServletContext().getContextPath()
							+ ACCESS_DENIED);
			return;
		}
		
		try {
			chain.doFilter(request, response);
			return;
		} catch (ServletException ex) {

			SecurityException rootCause = getSecurityCause(ex);

			if (rootCause != null) {
				handleSecurityException(response, rootCause);
				return;
			}
			throw ex;
		} catch (IOException ex) {

			SecurityException rootCause = getSecurityCause(ex);

			if (rootCause != null) {
				handleSecurityException(response, rootCause);
				return;
			}
			throw ex;
		}
	}
	
	private synchronized boolean possuiPermissao(String url, Usuario user) {
		
		NavigationSecurity navigationSecurity = (NavigationSecurity) WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext())
				.getBean("navigationSecurity");
		
		List<String> papeisDeAcesso = new ArrayList<>();
		List<String> papeisDoUsuario = new ArrayList<>();
		
		for (Recurso recurso : navigationSecurity.getAcesso().getRecursos()) {
			if(recurso.getUrl().equals(url)){
				
				populaPapeisDoUsuario(user, papeisDoUsuario);
				
				populaPapeisDeAcessoDaUrl(papeisDeAcesso, recurso);
				
				papeisDeAcesso.retainAll(papeisDoUsuario);
				if(papeisDeAcesso.size() > 0){
					return true;
				} 
				
				break;
			}
		}
		return false;
	}


	/**
	 * @param papeisDeAcesso
	 * @param acesso
	 */
	private synchronized void populaPapeisDeAcessoDaUrl(List<String> papeisDeAcesso,
			Recurso recurso) {
		for (PapelDeAcesso papelDeAcesso : recurso.getPapeis()) {
			papeisDeAcesso.add(papelDeAcesso.getNome());
		}
	}


	/**
	 * @param user
	 * @param papeisDoUsuario
	 */
	private synchronized void populaPapeisDoUsuario(Usuario user,
			List<String> papeisDoUsuario) {
		for (Papel papel : user.getPapeis()) {
			papeisDoUsuario.add(papel.getNome());
		}
	}

	private boolean isExcludeURL(String url) {
		for (String excludePages : EXCLUDE_PAGES) {
			if (url.contains(excludePages)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	protected SecurityException getSecurityCause(Throwable throwable) {
		if (throwable instanceof SecurityException) {
			return (SecurityException) throwable;
		}
		if (throwable.getCause() != null) {
			return getSecurityCause(throwable.getCause());
		} else {
			return null;
		}
	}

	protected void handleSecurityException(ServletResponse response,
			SecurityException ex) throws IOException {
		logger.debug("Acesso negado.", ex);
		logger.debug(ex.getMessage());
		logger.info("Acesso negado. Enviando para a página de login ");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(
				filterConfig.getServletContext().getContextPath() + LOGIN_PAGE);
	}

}
