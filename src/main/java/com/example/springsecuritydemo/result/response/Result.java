package com.example.springsecuritydemo.result.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> implements Serializable {

    @JsonProperty("repCode")
    private String code;

    @JsonProperty("repMsg")
    private String message;

    @JsonProperty("repData")
    @JsonSerialize(nullsUsing = ResultDataSerializer.class)
    private T data;

    public Result(@NonNull final ResultStatus resultStatus) {
        this.code = resultStatus.code();
        this.message = resultStatus.message();
    }

    public static <T> Result<T> of(@NonNull final ResultStatus resultStatus) {
        return Result.<T>builder()
                .resultStatus(resultStatus)
                .build();
    }

    public static <T> Result<T> ok() {
        return Result.<T>builder()
                .resultStatus(ResultStatus.OK)
                .build();
    }

    public static <T> Result<T> ok(final T data) {
        return Result.<T>builder()
                .resultStatus(ResultStatus.OK)
                .data(data)
                .build();
    }

    public T getData() {
        return this.data;
    }

    public void setData(@NonNull T data) {
        this.data = data;
    }

    public static class ResultBuilder<T> {

        public ResultBuilder<T> resultStatus(@NonNull final ResultStatus resultStatus) {
            this.code = resultStatus.code();
            this.message = resultStatus.message();
            return this;
        }
    }
}
