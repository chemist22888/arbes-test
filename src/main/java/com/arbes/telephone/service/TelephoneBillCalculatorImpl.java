package com.arbes.telephone.service;

import com.arbes.telephone.dto.Log;
import com.arbes.telephone.parser.LogParser;
import com.arbes.telephone.parser.LogParserCSV;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {
    public BigDecimal calculate(String phoneLog) {
        BigDecimal res = BigDecimal.ZERO;
        LogParser logParser = new LogParserCSV();

        List<Log> logs = logParser.parse(phoneLog);

        String frequentNumber = frequentNumber(logs.stream().map(Log::getPhone).collect(Collectors.toList()));


        System.out.println("a");
        for (Log log : logs) {
            if (log.getPhone().equals(frequentNumber))
                continue;

            int callDuration = (int) log.getStartDate().until(log.getEndDate(), ChronoUnit.MINUTES) + 1;

            int halfPriceMinutes = countHalfPriceMinutes(log.getStartDate().toLocalTime(), log.getEndDate().toLocalTime());
            int fullPriceMinutes = callDuration - halfPriceMinutes;
            int cheapMinutes = 0;

            if (callDuration > 5) {
                if (isAtExpesiveTime(log.getStartDate().toLocalTime())) {
                    if (fullPriceMinutes >= 5) {
                        fullPriceMinutes = 5;
                        cheapMinutes = callDuration - 5;
                        halfPriceMinutes = 0;
                    } else {
                        halfPriceMinutes = 5 - fullPriceMinutes;
                        cheapMinutes = callDuration - halfPriceMinutes - fullPriceMinutes;
                    }
                } else {
                    if (halfPriceMinutes >= 5) {
                        halfPriceMinutes = 5;
                        fullPriceMinutes = 0;
                        cheapMinutes = callDuration - 5;
                    } else {
                        fullPriceMinutes = 5 - halfPriceMinutes;
                        cheapMinutes = callDuration - halfPriceMinutes - fullPriceMinutes;
                    }
                }
            }

            res = res.add(BigDecimal.valueOf(fullPriceMinutes + halfPriceMinutes * 0.5 + cheapMinutes * 0.2));

        }


        return res;
    }

    private int countHalfPriceMinutes(LocalTime startTime, LocalTime endTime) {
        LocalTime halfPriceStartTime = LocalTime.parse("08:00:00");
        LocalTime halfPriceEndTime = LocalTime.parse("16:00:00");
        if (!isAtExpesiveTime(startTime))
            if (!isAtExpesiveTime(endTime))
                return (int) startTime.until(endTime, ChronoUnit.MINUTES) + 1;
            else
                return (int) startTime.until(halfPriceEndTime, ChronoUnit.MINUTES) + 1;
        else if (startTime.isBefore(halfPriceStartTime) && startTime.isAfter(halfPriceStartTime))
            return (int) Math.max(startTime.until(endTime, ChronoUnit.MINUTES),
                    halfPriceStartTime.until(halfPriceEndTime, ChronoUnit.MINUTES)) + 1;
        return 0;
    }

    private boolean isAtExpesiveTime(LocalTime time) {
        return time.isBefore(LocalTime.parse("08:00:00")) || time.isAfter(LocalTime.parse("16:00:00"));
    }

    private String frequentNumber(List<String> numbers) {
        if (numbers.size() == 0)
            return "";

        Map<String, Integer> numberFrequency = new HashMap<>();
        for (String number : numbers) {
            int frequency = 1;

            if (numberFrequency.containsKey(number))
                frequency = numberFrequency.get(number) + 1;

            numberFrequency.put(number, frequency);
        }
        int max = Collections.max(numberFrequency.values());

        Map<String, Integer> sums = numberFrequency.entrySet().stream().filter(entry -> entry.getValue() == max).collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> count(entry.getKey())
        ));

        String res = sums.entrySet().stream().filter(entry -> entry.getValue() == Collections.max(sums.values()))
                .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);


        return res;
    }

    private int count(String phone) {
        int sum = 0;
        for (int i = 0; i < phone.length(); i++) {
            try {
                sum += Integer.parseInt(phone.substring(i, i + 1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println(phone + " " + sum);
        return sum;
    }
}
