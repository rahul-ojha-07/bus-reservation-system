package com.example.brs.exception;


import com.example.brs.config.PropertiesConfig;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.MessageFormat;
import java.util.Optional;

@ControllerAdvice
public class BRSException {

    private static PropertiesConfig propertiesConfig;

    public BRSException(PropertiesConfig propertiesConfig) {
        BRSException.propertiesConfig = propertiesConfig;
    }


    public static RuntimeException throwException(String messageTemplate, String ...args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String ...args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, Long id, String ...args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType) + "." + id.toString();
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithTemplate(ExceptionType exceptionType, String messageTemplate, String ...args) {
        return throwException(exceptionType, messageTemplate, args);
    }

    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String ...args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        } else {
          return new RuntimeException(format(messageTemplate, args));
        }

    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return String.format("%s.%s", entityType.name(), exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String ...args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        return templateContent.map(s -> MessageFormat.format(s, (Object[]) args))
                .orElseGet(() -> String.format(template, (Object[]) args));
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }


}
