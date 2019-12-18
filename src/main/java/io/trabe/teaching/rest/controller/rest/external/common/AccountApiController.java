package io.trabe.teaching.rest.controller.rest.external.common;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.trabe.teaching.rest.controller.mapper.ExternalApiMapper;
import io.trabe.teaching.rest.controller.rest.external.annotation.CheckPrivileges;
import io.trabe.teaching.rest.controller.rest.external.annotation.ExternalApiCommon;
import io.trabe.teaching.rest.model.pojo.AccountKind;
import io.trabe.teaching.rest.model.pojo.api.external.common.ApiAccount;
import io.trabe.teaching.rest.model.pojo.api.external.common.ApiAccountCreationRequest;
import io.trabe.teaching.rest.model.service.AccountService;
import io.trabe.teaching.rest.model.service.UserService;

@RestController
@ExternalApiCommon
public class AccountApiController {

    private final AccountService accountService;
    private final UserService userService;
    private final ExternalApiMapper externalApiMapper;

    public AccountApiController(AccountService accountService,
            UserService userService, ExternalApiMapper externalApiMapper) {
        this.accountService = accountService;
        this.userService = userService;
        this.externalApiMapper = externalApiMapper;
    }

    @GetMapping("/users/{userId}/accounts")
    @CheckPrivileges
    public List<ApiAccount> getUserAccounts(@PathVariable Long userId) {
        return externalApiMapper
                .toAccountApiList(accountService.getUserAccounts(userService.getUser(userId).get().getLogin()));
    }

    @PostMapping(value = "/users/{userId}/accounts", consumes = "application/json")

    public ApiAccount createAccount(@RequestBody ApiAccountCreationRequest accountRequest, @PathVariable Long userId) {
        return externalApiMapper.toAccountApi(accountService
                .createAccount(userService.getUser(userId).get().getLogin(), accountRequest.getDescription(),
                        accountRequest.getCode(),
                        accountRequest.getBalance(),
                        AccountKind.valueOf(accountRequest.getKind().name()),
                        accountRequest.getCreationDate()));

    }


}
