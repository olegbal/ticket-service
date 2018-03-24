package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
