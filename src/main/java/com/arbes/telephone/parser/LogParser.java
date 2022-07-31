package com.arbes.telephone.parser;

import com.arbes.telephone.dto.Log;

import java.util.List;

public interface LogParser {
    List<Log> parse(String log);
}
