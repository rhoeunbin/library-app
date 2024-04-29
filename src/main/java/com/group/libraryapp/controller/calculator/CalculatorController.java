package com.group.libraryapp.controller.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 1. 이 클래스를 API의 진입 지점으로 만들어줌
public class CalculatorController {

    @GetMapping("/add") // 2. GET /add
    public int addTwoNumbers(@RequestParam int num1, @RequestParam int num2){
        return num1 + num2;
    }
}