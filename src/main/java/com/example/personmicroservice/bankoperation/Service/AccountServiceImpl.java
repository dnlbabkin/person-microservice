package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.CurrencyEnum;
import com.example.personmicroservice.bankoperation.DTO.TransferRequest;
import com.example.personmicroservice.bankoperation.Entity.Transaction;
import com.example.personmicroservice.bankoperation.Repositories.TransactionRepository;
import com.example.personmicroservice.bankservice.Clients.CBRClient;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import com.google.common.base.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final PersonAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CBRClient soapClient;

    @Override
    public Transaction sendMoney(TransferRequest transferRequest) throws JAXBException {
        String fromAccountNumber = transferRequest.getFromAccountNumber();
        String toAccountNumber = transferRequest.getToAccountNumber();
        String currency = transferRequest.getCurrency();
        BigDecimal amount = transferRequest.getAmount();

        PersonAccount fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        PersonAccount toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        AllData.MainIndicatorsVR.Currency currencyUsdRub = soapClient.getData().getCurrency();

//        BigDecimal currencyUsdRub = soapClient.getData().getCurrency().getUSD().getCurs();

        fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));
        accountRepository.save(fromAccount);
        BigDecimal result = convertCurrency(amount, fromAccount, toAccount, currencyUsdRub);
        toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(result));
        accountRepository.save(toAccount);

        Transaction transaction = transactionRepository.save(new Transaction(0L, toAccountNumber, currency, amount));

        return transaction;
    }

    private BigDecimal convertCurrency(BigDecimal amount, PersonAccount fromAccount, PersonAccount toAccount, AllData.MainIndicatorsVR.Currency  cur) {
        Function<AllData.MainIndicatorsVR.Currency, BigDecimal> exchangeFunction = CurrencyEnum.fromAbbreviation(fromAccount.getCurrentCurrency()).getExchangeFunction();
        BigDecimal currencyUsdRub123 = exchangeFunction.apply(cur);


        boolean isCurrencyEquals = fromAccount.getCurrentCurrency().equals(toAccount.getCurrentCurrency());
        BigDecimal result;
        if (isCurrencyEquals) {
            result = amount;
        } else if (fromAccount.getCurrentCurrency().equals("rub")) {
            result = amount.divide(currencyUsdRub123, 2, RoundingMode.HALF_UP);
        } else if (fromAccount.getCurrentCurrency().equals("usb")) {
            result = amount.multiply(currencyUsdRub123);
        } else {
            throw new RuntimeException("currency is unknown");
        }

        return result;
    }
}
