package com.piecepost.mail.https.requests.email;

import lombok.Getter;

@Getter
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
}
