package com.example.sreemoyeec662.inboxapp;

/**
 * Created by sreemoyeec662 on 01/04/2018.
 */

public class InboxMessageModel {
    private int id;
    private String gmailItemLetter;
    private String gmailFrom;
    private String gmailSubject;
    private String gmailMessage;
    private String gmailTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmailItemLetter() {
        return gmailItemLetter;
    }

    public void setGmailItemLetter(String gmailItemLetter) {
        this.gmailItemLetter = gmailItemLetter;
    }

    public String getGmailFrom() {
        return gmailFrom;
    }

    public void setGmailFrom(String gmailFrom) {
        this.gmailFrom = gmailFrom;
    }

    public String getGmailSubject() {
        return gmailSubject;
    }

    public void setGmailSubject(String gmailSubject) {
        this.gmailSubject = gmailSubject;
    }

    public String getGmailMessage() {
        return gmailMessage;
    }

    public void setGmailMessage(String gmailMessage) {
        this.gmailMessage = gmailMessage;
    }

    public String getGmailTimestamp() {
        return gmailTimestamp;
    }

    public void setGmailTimestamp(String gmailTimestamp) {
        this.gmailTimestamp = gmailTimestamp;
    }

    @Override
    public boolean equals(Object obj) {
        InboxMessageModel inboxMessageModel = (InboxMessageModel) obj;
        return (inboxMessageModel.getId() == id && inboxMessageModel.getGmailItemLetter().equals(gmailItemLetter) && inboxMessageModel.getGmailFrom().equals(gmailFrom)
                    && inboxMessageModel.getGmailSubject().equals(gmailSubject) && inboxMessageModel.getGmailMessage().equals(gmailMessage) && inboxMessageModel.getGmailTimestamp().equals(gmailTimestamp));
    }
}
