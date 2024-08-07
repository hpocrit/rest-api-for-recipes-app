package ru.kpfu.itis.gabdullina.MyRestApi.service;

import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;

import java.util.List;

public interface RecipeService {

    List<RecipeDto> getAllRecipes();

    RecipeDto getRecipeById(Long id);

    void save(RecipeDto recipe);

    void update(Long id, RecipeDto recipe);

    void delete(Long id);

}
