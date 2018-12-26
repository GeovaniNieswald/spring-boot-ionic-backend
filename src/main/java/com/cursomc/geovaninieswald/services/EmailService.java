package com.cursomc.geovaninieswald.services;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.geovaninieswald.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
