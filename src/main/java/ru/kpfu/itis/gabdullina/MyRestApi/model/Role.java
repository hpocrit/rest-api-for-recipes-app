package ru.kpfu.itis.gabdullina.MyRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "roles")
@Entity
public class Role extends BaseModel{

    private String name;
    @OneToMany
    private List<User> users;

}
