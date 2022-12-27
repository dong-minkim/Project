package com.dutmdcjf.backendserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
