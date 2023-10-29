package com.example.brs.exception;

public interface StandardExceptionMessage {
    /**
     * @param entityType
     * @param exceptionType
     * @param args
     * @return {@link RuntimeException}
     */
    default RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String ...args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return {@link RuntimeException}
     */
    default RuntimeException exceptionWithId(EntityType entityType, ExceptionType exceptionType, Long id, String... args) {
        return BRSException.throwExceptionWithId(entityType, exceptionType, id, args);
    }
}
