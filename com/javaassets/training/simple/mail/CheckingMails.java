package com.javaassets.training.simple.mail;


import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class CheckingMails {

   public static void check(String host, String port,  String storeType, String user,
      String password) 
   {
      try {

      //create properties field
      Properties properties = new Properties();
      //pop3 settings
      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", port);
      properties.put("mail.pop3.starttls.enable", "true");
      
      //imap settings
      /*properties.put("mail.imaps.host", host);
      properties.put("mail.imaps.port", port);
      properties.put("mail.imaps.starttls.enable", "true");*/
      Session emailSession = Session.getDefaultInstance(properties);
      
      /*Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication(user, password);
    	    }
    	});*/
  
      //create the POP3 store object and connect with the pop server
      
      Store store = emailSession.getStore(storeType);

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);
      
      int vouchers = 0;
      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];
        
         if(message.getSubject().contains("Confirm Hotel Booking")){
        	 System.out.println("---------------------------------");
        	 ++vouchers;
	         System.out.println("Email Number " + (i + 1));
	         System.out.println("Subject: " + message.getSubject());
	         System.out.println("From: " + message.getFrom()[0]);
	         System.out.println("Text: " + message.getContent().toString());
         }

      }
      
      System.out.println("No. of vouchers existing : "+vouchers);
      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {

      /*String host = "imap.gmail.com";// change accordingly
      String port = "993";
      String mailStoreType = "imaps";
      String username = "vistaramrooms@gmail.com";// change accordingly
      String password = "vistaram@66669";// change accordingly*/
      
	   
	   String host = "pop.gmail.com";// change accordingly
	   String mailStoreType = "pop3s";
	   String username = "vistaramrooms@gmail.com";// change accordingly
	   String password = "vistaram@66669";// change accordingly */   
	   String port = "995";
     /* String host = "vistaram.com";// change accordingly
      String mailStoreType = "pop3";
      String username = "info@vistaram.com";// change accordingly
      String password = "^z]teJspdI6)";// change accordingly */

      check(host, port , mailStoreType, username, password);

   }

}