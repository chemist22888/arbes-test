package com.arbes.telephone.parser;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.arbes.telephone.dto.Log;

import java.io.StringReader;
import java.util.List;

public class LogParserCSV implements LogParser {
    public List<Log> parse(String log) {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();
        CsvToBean<Log> parserBean = new CsvToBeanBuilder(new StringReader(log)).withType(Log.class).build();


        return parserBean.parse();
    }
}
