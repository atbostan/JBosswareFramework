package com.bossware.jboss.application.features.user.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public interface UserLoginAttemptService {

    void add(String userName) throws ExecutionException;
    void evict(String userName);
    boolean checkIfMaxAttempt(String userName) throws ExecutionException;

}
