package io.trabe.teaching.rest.model.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.jooq.lambda.Seq;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {


    private final ConcurrentHashMap<String, List<Long>> storage;

    public AuthorizationServiceImpl() {
        storage = new ConcurrentHashMap<>();
        storage.putIfAbsent("my-client", Seq.of(1L).toList());
    }

    @Override
    public List<Long> getAuthorizedUsersForLogin(String login) {
        return storage.get(login);
    }
}
