package com.github.olegbal.ticketservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    
    @Column(name = "role_name")
    private String roleName;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return getRoleName();
    }
}
