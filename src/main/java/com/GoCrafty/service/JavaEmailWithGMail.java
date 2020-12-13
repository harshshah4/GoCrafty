package com.GoCrafty.service;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmailWithGMail
{
    Session mailSession;

    public static void Email(String to, String msg) throws AddressException, MessagingException
    {
    	
    	JavaEmailWithGMail javaEmail = new JavaEmailWithGMail();
    	javaEmail.setMailServerProperties();
    	javaEmail.draftEmailMessage(to,msg);
    	javaEmail.sendEmail(to,msg);
    }

    public void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }

    public MimeMessage draftEmailMessage(String to,String msg) throws AddressException, MessagingException
    {
        String[] toEmails = { to};
        String emailSubject = "Critical Security Alert";
        String emailBody = msg;
        MimeMessage emailMessage = new MimeMessage(mailSession);
        /**
         * Set the mail recipients
         * */
        for (int i = 0; i < toEmails.length; i++)
        {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
        }
        emailMessage.setSubject(emailSubject);
        /**
         * If sending HTML mail
         * */
        emailMessage.setContent(emailBody, "text/html");
        /**
         * If sending only text mail
         * */
        //emailMessage.setText(emailBody);// for a text email
        return emailMessage;
    }

    public void sendEmail(String to,String msg) throws AddressException, MessagingException
    {
        /**
         * Sender's credentials
         * */
        String fromUser = "gocrafty2020@gmail.com";
        String fromUserEmailPassword = "ENPM613GOCRAFTY";

        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessage(to,msg);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
}