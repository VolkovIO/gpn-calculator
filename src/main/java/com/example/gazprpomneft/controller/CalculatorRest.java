package com.example.gazprpomneft.controller;

import com.example.gazprpomneft.calculator.CalculatorClient;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest")
@Data
public class CalculatorRest {

    public final CalculatorClient client;

    @PostMapping(value = "/add")
    public int add(@RequestBody Map<String, Integer> data) {
        return client.calcAdd(data.get("intA"), data.get("intB")).getAddResult();
    }

    @PostMapping(value = "/divide")
    public int divide(@RequestBody Map<String, Integer> data) {
        return client.calcDivide(data.get("intA"), data.get("intB")).getDivideResult();
    }

    @PostMapping(value = "/multiply")
    public int multiply(@RequestBody Map<String, Integer> data) {
        return client.calcMultiply(data.get("intA"), data.get("intB")).getMultiplyResult();
    }

    @PostMapping(value = "/subtract")
    public int subtract(@RequestBody Map<String, Integer> data) {
        return client.calcSubtract(data.get("intA"), data.get("intB")).getSubtractResult();
    }

}
