package com.group.libraryapp.dto.calculator.request;

import java.util.ArrayList;
import java.util.List;

public class ArrayRequest {
    List<Integer> numbers;

    public ArrayRequest() {

    }

    public ArrayRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
