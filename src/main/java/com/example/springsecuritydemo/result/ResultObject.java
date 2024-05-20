package com.example.springsecuritydemo.result;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultObject {
    private boolean status;
    private int code;
    private String message;

    public ResultObject(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResultObject createInstance(boolean status, int code, String message) {
        return new ResultObject(status, code, message);
    }

    public static ResultObject createInstance(boolean status, String message) {
        return new ResultObject(status, message);
    }

    @Override
    public String toString() {
        return "ResultObject{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}