package br.unifor.saa.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public static void enviaEmail(String email, String senha){
		
		Properties props = new Properties();
		/** Parâmetros de conexão com servidor Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("saa.unifor2016@gmail.com", "unifor2016");
			}
		});

		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("seuemail@gmail.com")); // Remetente

			Address[] toUser = InternetAddress // Destinatário(s)
					.parse(email);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Bem-vindo ao Saa-Web");// Assunto
			message.setText("Utilize esta senha, <b>"+senha+"</b> para autenticar-se.");
			/** Método para enviar a mensagem criada */
			Transport.send(message);

			System.out.println("Foi!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		enviaEmail("adrianopatrick@gmail.com", "teste");
	}
}
