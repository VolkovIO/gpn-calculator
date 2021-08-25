package com.example.gazprpomneft.controller;

import com.example.gazprpomneft.calculator.CalculatorClient;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@Data
public class CalculatorRest {

    public final CalculatorClient client;

    @PostMapping(value = "/add")
    public Map<String, Integer> add(@RequestBody Map<String, Integer> data) {
        return Collections.singletonMap("AddResult",
                client.calcAdd(data.get("intA"), data.get("intB")).getAddResult());
    }

    @PostMapping(value = "/divide")
    public Map<String, Integer> divide(@RequestBody Map<String, Integer> data) {
        return Collections.singletonMap("DivideResult",
                client.calcDivide(data.get("intA"), data.get("intB")).getDivideResult());
    }

    @PostMapping(value = "/multiply")
    public Map<String, Integer> multiply(@RequestBody Map<String, Integer> data) {
        return Collections.singletonMap("Multiply",
                client.calcMultiply(data.get("intA"), data.get("intB")).getMultiplyResult());
    }

    @PostMapping(value = "/subtract")
    public Map<String, Integer> subtract(@RequestBody Map<String, Integer> data) {
        return Collections.singletonMap("SubtractResult",
                client.calcSubtract(data.get("intA"), data.get("intB")).getSubtractResult());
    }

}
