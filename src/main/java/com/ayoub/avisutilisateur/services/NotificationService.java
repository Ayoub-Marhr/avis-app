package com.ayoub.avisutilisateur.services;

import com.ayoub.avisutilisateur.entities.Validation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class NotificationService {
    @Autowired
    JavaMailSender mailSender;
    public void envoyer(Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("AyoubAvis@avis.tech");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("votre code d'activation");
        String text = String.format( "<html>" +
                        "<body>" +
                        "<p>Bonjour %s,</p>" +
                        "<p>Votre code d'activation est : <strong>%s</strong></p>" +
                        "<p>Cordialement,<br/>L'équipe Ayoub Avis</p>" +
                        "</body>" +
                        "</html>",
                validation.getUser().getUsername(),
                validation.getCode()
        );
        message.setText(text);
        mailSender.send(message);

    }
}
