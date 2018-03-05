package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByLogin(String login);

}
