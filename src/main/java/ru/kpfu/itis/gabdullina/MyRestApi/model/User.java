package ru.kpfu.itis.gabdullina.MyRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
public class User extends BaseModel {

    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
