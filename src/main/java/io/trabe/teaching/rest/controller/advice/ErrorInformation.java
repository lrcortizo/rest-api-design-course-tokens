package io.trabe.teaching.rest.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
class ErrorInformation {
    private String exception;
}
