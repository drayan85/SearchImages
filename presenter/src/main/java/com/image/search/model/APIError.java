package com.image.search.model;

/**
 * @author Paramanathan Ilanthirayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 13th of July 2021
 */
public class APIError {

    private int status; //not HTTP header response code, it's custom status code from response body
    private String message; // custom message from HTTP Response Body
    private String error; //custom error message from HTTP Response Body

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        if (message == null || message.isEmpty()) {
            if (error != null) {
                return error;
            } else {
                return " ";
            }
        } else {
            return message;
        }
    }

    public String getError() {
        return error;
    }
}
