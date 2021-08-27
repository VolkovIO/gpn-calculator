package com.example.gazprpomneft.controller;

import com.example.gazprpomneft.calculator.CalculatorClient;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@Data
public class CalculatorRest {

    public final CalculatorClient client;

    @PostMapping(value = "/add")
    public Map<String, Integer> add(@Valid @RequestBody ValidateValue data) {
        return Collections.singletonMap("AddResult",
                client.calcAdd(data.getIntA(), data.getIntB()).getAddResult());
    }

    @PostMapping(value = "/divide")
    public Map<String, Integer> divide(@Valid @RequestBody ValidateValue data) {
        if (data.getIntB() == 0){
            throw new ArithmeticException("Деление на 0");
        }

        return Collections.singletonMap("DivideResult",
                client.calcDivide(data.getIntA(), data.getIntB()).getDivideResult());
    }

    @PostMapping(value = "/multiply")
    public Map<String, Integer> multiply(@Valid @RequestBody ValidateValue data) {
        return Collections.singletonMap("Multiply",
                client.calcMultiply(data.getIntA(), data.getIntB()).getMultiplyResult());
    }

    @PostMapping(value = "/subtract")
    public Map<String, Integer> subtract(@Valid @RequestBody ValidateValue data) {
        return Collections.singletonMap("SubtractResult",
                client.calcSubtract(data.getIntA(), data.getIntB()).getSubtractResult());
    }

    @Data
    private static class ValidateValue{

        @Min(Integer.MIN_VALUE)
        @Max(Integer.MAX_VALUE)
        private int intA;

        @Min(Integer.MIN_VALUE)
        @Max(Integer.MAX_VALUE)
        private int intB;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
