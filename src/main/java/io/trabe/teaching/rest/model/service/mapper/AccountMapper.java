package io.trabe.teaching.rest.model.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.trabe.teaching.rest.model.pojo.Account;
import io.trabe.teaching.rest.model.pojo.backend.BackendAccount;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountKind", target = "kind")
    Account convert(BackendAccount backendAccount);

    List<Account> convert(List<BackendAccount> backendAccounts);
}
