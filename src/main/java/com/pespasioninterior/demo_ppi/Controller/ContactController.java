package com.pespasioninterior.demo_ppi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pespasioninterior.demo_ppi.Dto.ContactDto;
import com.pespasioninterior.demo_ppi.Security.Controller.Mensaje;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "localhost:4200")
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendemail")
    public ResponseEntity<?> sendEmail(@RequestBody ContactDto contact) {
        try {
        	System.out.println(contact.toString());
            SimpleMailMessage message = new SimpleMailMessage();
            //message.setFrom(contact.getEmailFrom());
            //message.setTo(contact.getEmailTo());
            message.setTo("ppi.code.sl@gmail.com");
            message.setSubject(contact.getEmailSubject());
            message.setText("Correo enviado desde: " + contact.getEmailFrom() + "\nTelefono de contacto: " + contact.getNumberPhone() + ": \n\nMensaje:\n\n" + contact.getEmailText());
            mailSender.send(message);
            return new ResponseEntity<>(new Mensaje("Correo electrónico enviado correctamente."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al enviar el correo electrónico: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
