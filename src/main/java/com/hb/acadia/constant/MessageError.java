package com.hb.acadia.constant;

public enum MessageError {

    ERROR_CONNECTION_DATABASE("Une erreur s'est produite lors de la récupération de l'utilisateur en base de données");

    private String message;

   private MessageError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
