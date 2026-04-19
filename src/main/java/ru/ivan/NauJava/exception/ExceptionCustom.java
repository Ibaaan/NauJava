package ru.ivan.NauJava.exception;

public class ExceptionCustom {
    private String message;

    private ExceptionCustom(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Exception create(Throwable e) {
        return new Exception(e.getMessage());
    }

    public static Exception create(String message) {
        return new Exception(message);
    }
}
