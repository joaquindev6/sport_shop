package com.jfarro.app.validators;

import org.springframework.validation.Errors;

public class UtilValidation {

    /* VALIDACION DE EMAIL */
    public static void emailRegex(String email, Errors errors) {
        if (email != null && !email.isBlank()) {
            if (email.matches("([.]?[a-zA-Z0-9.]+[.]?)@([a-zA-Z][^.][a-zA-Z0-9.]+)\\.([a-zA-Z.]+[^.][a-zA-Z]+)")) { //Valida si el formato de @.. es correcto
                String[] data = email.split("@");
                if (data[0].matches("[.][a-zA-Z0-9.]+[.]")) { //Valida si hay puntos al inicio y al final
                    errors.rejectValue("email", "EmailRegex.email.validation2");
                } else if (data[0].matches("[.][a-zA-Z0-9.]+")) { //Valida si hay punto al inicio
                    errors.rejectValue("email", "EmailRegex.email.validation3");
                } else if (data[0].matches("[a-zA-Z0-9.]+[.]")) { //Valida si hay punto al final
                    errors.rejectValue("email", "EmailRegex.email.validation4");
                } else if (!data[0].matches("[a-zA-Z0-9.]+")) { //Valida si hay caracteres especiales aparte del punto
                    errors.rejectValue("email", "EmailRegex.email.invalid");
                } else if (!data[0].matches("([a-zA-Z0-9.]+){6,30}")) { //Valida si tiene un minimo de 6 y maximo de 30
                    errors.rejectValue("email", "EmailRegex.email.validation6");
                }
            } else {
                errors.rejectValue("email", "EmailRegex.email.invalid");
            }
        }
    }
}
