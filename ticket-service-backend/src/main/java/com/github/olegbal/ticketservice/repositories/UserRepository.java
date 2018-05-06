package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByLogin(String login);

    List<User> findAllByRoles(Set<Role> role);

}
