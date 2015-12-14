/**
 * 
 */
package br.implement.system.avocatus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
public class MessagesResources {
	
	private static FileInputStream file = null;
	private static Locale locale;
	
	private static Properties getProp() throws IOException {
		Properties properties = new Properties();
		
		
		if(locale.equals(Locale.US)){
			file = new FileInputStream(new File("resources/messages_en_US.properties"));
		} else {
			file = new FileInputStream(new File("resources/messages_pt_BR.properties"));
		}
		properties.load(file);
		return properties;
	}
	
	public static String getMessages(String key) throws IOException{
		return getProp().getProperty(key);
	}

	public static void setLocale(Locale locale) {
		MessagesResources.locale = locale;
	}
	
}
