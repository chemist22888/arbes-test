package com.arbes.telephone;

import com.arbes.telephone.parser.LogParserCSV;
import com.arbes.telephone.service.TelephoneBillCalculatorImpl;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
//        System.out.println(new TelephoneBillCalculatorImpl().calculate("420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
//                        "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n"+
//                "420774577955,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
//                "420774577955,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
//
//                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00"));

        System.out.println(LocalTime.parse("18:10:15").until(LocalTime.parse("18:11:57"),ChronoUnit.MINUTES));

    }
}
