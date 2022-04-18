package com.example.personmicroservice;

import com.google.common.base.Function;

import java.math.BigDecimal;
import java.util.HashMap;

public enum CurrencyEnum {
    USD("usd", (AllData.MainIndicatorsVR.Currency curr) -> curr.getUSD().getCurs()),
    EUR("eur", (AllData.MainIndicatorsVR.Currency curr) -> curr.getEUR().getCurs());

    String abbreviation;
    Function<AllData.MainIndicatorsVR.Currency, BigDecimal> exchangeFunction;
    static HashMap<String, CurrencyEnum> currencyEnumHashMap = new HashMap<>();
    CurrencyEnum(String abbreviation, Function<AllData.MainIndicatorsVR.Currency, BigDecimal> function) {
        this.abbreviation = abbreviation;
        this.exchangeFunction = function;

//        currencyEnumHashMap.put(abbreviation, this);
    }

    public static CurrencyEnum fromAbbreviation(String abbr) {
        return currencyEnumHashMap.get(abbr);
    }

    public Function<AllData.MainIndicatorsVR.Currency, BigDecimal> getExchangeFunction() {
        return exchangeFunction;
    }
}
