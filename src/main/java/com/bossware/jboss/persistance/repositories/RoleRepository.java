package com.bossware.jboss.persistance.repositories;

import com.bossware.jboss.domain.entities.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Role[] findAllByUserId(long id);
}