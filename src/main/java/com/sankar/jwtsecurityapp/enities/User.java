package com.sankar.jwtsecurityapp.enities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
            @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
            }
    )

    @Getter
    @Setter
    private Set<Role> role;
}
