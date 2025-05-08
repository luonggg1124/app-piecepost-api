package com.piecepost.keys.auth.tokens;
public enum TokenType {
    ACCESS("access"),
    REFRESH("refresh");

    public static final String STRING_KEY = "tokenType";
    public static final String BEARER_STRING_KEY = "bearer";
    private final String value;
    TokenType(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
