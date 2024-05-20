package com.example.springsecuritydemo.result.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum ResultStatus {

    OK("0000", "成功");

    private final String code;

    private final String message;
}
