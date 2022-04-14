package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankoperation.Entity.Transaction;
import com.example.personmicroservice.bankoperation.DTO.TransferRequest;
import com.example.personmicroservice.bankoperation.Repositories.TransactionRepository;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class AccountServiceImpl implements AccountService {

    private final PersonAccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final SoapClient soapClient;

    @Autowired
    public AccountServiceImpl(PersonAccountRepository accountRepository, TransactionRepository transactionRepository, SoapClient soapClient) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.soapClient = soapClient;
    }

    @Override
    public Transaction sendMoney(TransferRequest transferRequest) throws JAXBException {
        String fromAccountNumber = transferRequest.getFromAccountNumber();
        String toAccountNumber = transferRequest.getToAccountNumber();
        String currency = transferRequest.getCurrency();
        BigDecimal amount = transferRequest.getAmount();

        PersonAccount fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        PersonAccount toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);

        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData().getMainIndicatorsVR()
                .getCurrency().getUSD().getCurs();

        if (fromAccount.getCurrentCurrency().equals(toAccount.getCurrentCurrency())) {
            fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));

            accountRepository.save(fromAccount);

            toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(amount));

            accountRepository.save(toAccount);
        } else if (!(fromAccount.getCurrentCurrency().equals(toAccount.getCurrentCurrency()))
                && fromAccount.getCurrentCurrency().equals("rub")) {
            fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));

            accountRepository.save(fromAccount);

            BigDecimal result = amount.divide(usd, 2, RoundingMode.HALF_UP);

            toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(result));

            accountRepository.save(toAccount);
        } else if (!(fromAccount.getCurrentCurrency().equals(toAccount.getCurrentCurrency()))
                && fromAccount.getCurrentCurrency().equals("usd")) {
            fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));

            accountRepository.save(fromAccount);

            BigDecimal result = amount.multiply(usd);

            toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(result));

            accountRepository.save(toAccount);
        }

        Transaction transaction = transactionRepository.save(new Transaction(0L, toAccountNumber, currency, amount));

        return transaction;
    }
}
