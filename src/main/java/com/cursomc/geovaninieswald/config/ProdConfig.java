package com.cursomc.geovaninieswald.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.geovaninieswald.services.EmailService;
import com.cursomc.geovaninieswald.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
		
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
