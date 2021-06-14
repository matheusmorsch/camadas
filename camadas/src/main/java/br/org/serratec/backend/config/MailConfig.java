package br.org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void enviarEmail (String para, String assunto, String texto) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("matheus.morsch@estudante.firjan.senai.br");
		simpleMailMessage.setTo(para);
		simpleMailMessage.setSubject(assunto);
		simpleMailMessage.setText("Dados do usuario\n" + texto + "\n\n\n\n Serratec Residencia de Software - 2021");
		javaMailSender.send(simpleMailMessage);
	}
}
