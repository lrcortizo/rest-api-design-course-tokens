package io.trabe.teaching.rest.model.accessor.publicapi;

import java.util.List;

import io.trabe.teaching.rest.model.pojo.Account;

public interface PublicapiAccessor {

    List<Account> getUserAccounts(Long userId);

    Account createAccount(Long userId, Account account);

    Account getAccount(Long id);
}
