package ru.kpfu.itis.gabdullina.MyRestApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "recipes")
@Entity
public class Recipe extends BaseModel {

    private String title;
    private String image;

}
