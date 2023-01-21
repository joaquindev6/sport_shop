package com.jfarro.app.validators;

import com.jfarro.app.models.domains.Client;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("client")
public class ClientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        UtilValidation.emailRegex(client.getEmail(), errors);
    }
}
