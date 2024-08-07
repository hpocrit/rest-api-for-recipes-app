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
@Table(name = "comments")
@Entity
public class Comment extends BaseModel {
    private Long recipeId;
    private String value;
    private Float rating;
}
