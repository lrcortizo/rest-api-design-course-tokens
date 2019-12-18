package io.trabe.teaching.rest.model.service;

import java.util.List;

import org.springframework.stereotype.Component;

import io.trabe.teaching.rest.model.accessor.publicapi.PublicapiAccessor;
import io.trabe.teaching.rest.model.pojo.Account;

@Component
public class ConsumeApiServiceImpl implements ConsumeApiService {

    private final PublicapiAccessor publicapiAccessor;

    public ConsumeApiServiceImpl(PublicapiAccessor publicapiAccessor) {
        this.publicapiAccessor = publicapiAccessor;
    }

    @Override
    public List<Account> getAccountsByUserLogin(Long userId) {
        return publicapiAccessor.getUserAccounts(userId);
    }

    @Override
    public Account createAccount(Long userId, Account account) {
        return publicapiAccessor.createAccount(userId, account);
    }
}
