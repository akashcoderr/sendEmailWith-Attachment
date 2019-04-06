/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendemail;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author The Robust Coder
 */
public class SendEmail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Preparing To Send Email..");
        Properties properties=new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="prajapatiakash125@gmail.com";
        String password="*******";
        String toEmail="saurabhkumarverma066@gmail.com";
        
        Session session=Session.getInstance(properties,new Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message message =new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Subject Line of my java program..");
             Multipart emailContetnt=new MimeMultipart();
             
             //text body
             MimeBodyPart textBodyPart=new MimeBodyPart();
             textBodyPart.setText("i can add multipal line of text...i am send by java");
             
             
             //attachment body part
             MimeBodyPart pdfAttachment=new MimeBodyPart();
             pdfAttachment.attachFile("C:\\Users\\Rajiv Pathak\\Downloads\\MIT TYBCA Sem 4 Advanced Java.pdf");
             
             // attach body part
             emailContetnt.addBodyPart(textBodyPart);
             emailContetnt.addBodyPart(pdfAttachment);
             
             
             //attach multipart to message
             message.setContent(emailContetnt);
             
             Transport.send(message);
             System.out.println("Message Sent:)");
             
             
            
        } catch (MessagingException ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
