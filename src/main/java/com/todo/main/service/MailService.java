package com.todo.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService 
{

    @Autowired
    private JavaMailSender mailSender;

    public void sendInterviewListToAdmin(String subject, String content, boolean isHtml) 
    {
        sendEmail("narasimhamurthy.ns646@gmail.com", subject, content, isHtml);
    }

    public void sendInterviewReminderToUser(String subject, String content, String toEmail, boolean isHtml) 
    {
        sendEmail(toEmail, subject, content, isHtml);
    }

    private void sendEmail(String toEmail, String subject, String content, boolean isHtml) 
    {
        try 
        {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, isHtml);
            mailSender.send(message);
        } 
        catch (MessagingException e) 
        {
            e.printStackTrace();
        }
    }
}
