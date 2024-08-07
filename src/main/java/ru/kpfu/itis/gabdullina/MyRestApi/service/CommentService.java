package ru.kpfu.itis.gabdullina.MyRestApi.service;

import ru.kpfu.itis.gabdullina.MyRestApi.dto.CommentDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllCommentsByRecipeId(Long id);
    void save(CommentDto commentDto);

}
