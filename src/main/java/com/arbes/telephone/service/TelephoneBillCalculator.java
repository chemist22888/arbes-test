package com.arbes.telephone.service;

import java.math.BigDecimal;

public interface TelephoneBillCalculator {
    BigDecimal calculate(String phoneLog);
}