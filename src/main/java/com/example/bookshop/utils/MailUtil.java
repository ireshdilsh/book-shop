package com.example.bookshop.utils;

import com.example.bookshop.controller.PasswordController;
import javafx.scene.text.Font;

import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil {
    public static void sendMail(String receipt) throws Exception{

        System.out.println("Mail Send Preparing.");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "ireshdilshan46@gmail.com";
        String password = "vhsv nryj agmz lpqc";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccount, password);
            }
        });

        Message message = prepareMessage(session,myAccount,receipt);
        Transport.send(message);
        System.out.println("Message Sent Successfully.");
    }

    static int confirmationNumber;

    public int generateNumber(){
        Random random = new Random();
        confirmationNumber = random.nextInt(1000000);

        while (true){
            if(confirmationNumber > 100000 && confirmationNumber < 1000000){
                return confirmationNumber;
            }
        }
    }

    private static Message prepareMessage(Session session, String myAccount, String receipt) throws Exception {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccount));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receipt));
        message.setSubject("Welcme to Novel Nook. ");
        message.setText("Your Confirmation Number is \n\n\t\t\t\t" + confirmationNumber);
        return message;
    }
}
