package com.group.libraryapp.dto.calculator.request;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayResponse {
    private DayOfWeek dayOfWeek;

    public DayResponse(LocalDate localDate) {
        this.dayOfWeek = localDate.getDayOfWeek();
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
