package ru.kpfu.itis.gabdullina.MyRestApi.service;

import ru.kpfu.itis.gabdullina.MyRestApi.dto.SimilarRecipeDto;

import java.util.List;

public interface SimilarRecipesService {

    public List<SimilarRecipeDto> getSimilarRecipes(String recipeName);

}
