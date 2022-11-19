package com.bossware.jboss.persistance.repositories;

import com.bossware.jboss.domain.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    List<Role> findAllByUserId(long id);

    @Query("SELECT r.roleName from roles r,users u where u.id=r.user.id AND u.userName=:userName")
    List<Role> findRolesByUserName(@Param("userName") String userName);

    @Query("SELECT r.roleName from roles r,users u where u.id=r.user.id AND u.email=:email")
    List<Role> findROlesByUserEmail(@Param("email") String email);
}
