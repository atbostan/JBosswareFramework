package com.bossware.jboss.persistance.repositories;

import com.bossware.jboss.domain.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findUserByUserName(String userName);
    User findUserByEmail(String email);
}
