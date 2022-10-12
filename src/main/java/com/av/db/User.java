package com.av.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @ManyToMany(targetEntity = Role.class,fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "role_fk"))
    private List<Role> role =new ArrayList<>();

}
