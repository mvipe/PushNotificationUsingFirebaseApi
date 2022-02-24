package com.learn.pushnotificationusingapi;

public class NotificationData {
    private String title;
    private String message;

    public NotificationData(String message,String title) {
        this.message = message;
        this.title=title;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
