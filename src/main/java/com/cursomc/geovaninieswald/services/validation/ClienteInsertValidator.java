package com.cursomc.geovaninieswald.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cursomc.geovaninieswald.domain.enums.TipoCliente;
import com.cursomc.geovaninieswald.dto.ClienteNewDTO;
import com.cursomc.geovaninieswald.resources.exceptions.FieldMessage;
import com.cursomc.geovaninieswald.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

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

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}