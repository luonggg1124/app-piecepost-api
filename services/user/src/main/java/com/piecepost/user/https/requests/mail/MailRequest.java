package com.piecepost.user.https.requests.mail;

import lombok.Getter;

@Getter
public class MailRequest {
    private String to;
    private String subject;
    private String body;
}
