package com.bossware.jboss.persistance.repositories;

import com.bossware.jboss.domain.entities.Auth;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends PagingAndSortingRepository<Auth,Long> {
    Auth[] findAllByRoleId(long id);
}
