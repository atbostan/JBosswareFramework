package com.bossware.jboss.persistance.repositories;

import com.bossware.jboss.domain.entities.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    List<Role> findAllByUserId(long id);
}
