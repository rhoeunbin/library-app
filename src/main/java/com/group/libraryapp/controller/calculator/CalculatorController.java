package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController // 1. 이 클래스를 API의 진입 지점으로 만들어줌
public class CalculatorController {

    @GetMapping("/add") // 2. GET /add
    public int addTwoNumbers(CalculatorAddRequest request){
        return request.getNum1() + request.getNum2();
    }

    @PostMapping("/multiply") // POST /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNum1() * request.getNum2();
    }

    // 과제2
    @GetMapping("/api/v1/calc") // 2. GET /add
    public CalculatorResponse calculator(CalculatorRequest request){
        return new CalculatorResponse(request);
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DayResponse dayOfWeek(@RequestParam("date") String date){
        LocalDate localDate = LocalDate.parse(date);
        return new DayResponse(localDate);
    }

    @PostMapping("/api/v1/arr")
    public int sum(@RequestBody ArrayRequest request) {
        int res = 0;
        for(int i = 0; i < request.getNumbers().size(); i++) {
            res += request.getNumbers().get(i);
        }

        return res;
    }
}