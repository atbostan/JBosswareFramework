package com.bossware.jboss.application.features.user.impl;

import com.bossware.jboss.application.features.user.services.UserLoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginAttemptServiceImpl implements UserLoginAttemptService {

    public static final int  MAX_LOGGING_ATTEMPT =5;
    public static final int  ATTEMPT_CACHE_DURATION =15;
    private static final int ATTEMPT_INCREMENT=1;

    private LoadingCache<String,Integer> loggingAttemptCache;

    public UserLoginAttemptServiceImpl() {
        loggingAttemptCache= CacheBuilder.newBuilder().expireAfterWrite(ATTEMPT_CACHE_DURATION, TimeUnit.MINUTES).maximumSize(100).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String s) throws Exception {
                return 0;
            }
        });
    }

    @Override
    public void add(String userName) throws ExecutionException {
        int attempts = 0;
        attempts = ATTEMPT_INCREMENT +loggingAttemptCache.get(userName);
        loggingAttemptCache.put(userName,attempts);
    }

    @Override
    public void evict(String userName) {
        loggingAttemptCache.invalidate(userName);

    }

    @Override
    public boolean checkIfMaxAttempt(String userName) throws ExecutionException {
        return loggingAttemptCache.get(userName) >= MAX_LOGGING_ATTEMPT;
    }
}
