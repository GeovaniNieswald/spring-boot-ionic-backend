package com.cursomc.geovaninieswald.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.geovaninieswald.domain.Cliente;
import com.cursomc.geovaninieswald.domain.enums.TipoCliente;
import com.cursomc.geovaninieswald.dto.ClienteNewDTO;
import com.cursomc.geovaninieswald.repositories.ClienteRepository;
import com.cursomc.geovaninieswald.resources.exceptions.FieldMessage;
import com.cursomc.geovaninieswald.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfCnpj()))
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));

		if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfCnpj()))
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null)
			list.add(new FieldMessage("email", "E-mail já existente"));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}