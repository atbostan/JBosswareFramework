package com.bossware.jboss.application.base;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceGenericBase<P,R> {
    ResponseEntity<R> create(P p) throws Exception;

    ResponseEntity<R> getEntityById(long id);

    ResponseEntity<R> update(long id, P p);

    ResponseEntity<R> delete(long id);

    ResponseEntity<List<R>> getAll(int page, int limit);
}
