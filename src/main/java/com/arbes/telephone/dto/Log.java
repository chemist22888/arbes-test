package com.arbes.telephone.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Log {
    @CsvBindByPosition(position = 0)
    String phone;

    @CsvDate(value = "dd-MM-yyyy HH:mm:ss")
    @CsvBindByPosition(position = 1)
    private LocalDateTime startDate;

    @CsvDate(value = "dd-MM-yyyy HH:mm:ss")
    @CsvBindByPosition(position = 2)
    private LocalDateTime endDate;
}
