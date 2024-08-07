package ru.kpfu.itis.gabdullina.MyRestApi.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.SimilarRecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.service.SimilarRecipesService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimilarRecipesServiceImpl implements SimilarRecipesService {
    @JsonNaming
    record Recipe(String title, String image) {};

    @JsonNaming
    record ApiResponse(@JsonProperty("results") List<Recipe> recipes) {};

    @Value("${api-key}")
    private String key;

    private final RestTemplate restTemplate;

    private List<SimilarRecipeDto> res = new ArrayList<>();

    @Override
    public List<SimilarRecipeDto> getSimilarRecipes(String recipeName) {
        String url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=%s&query=%s&number=10".formatted(key, recipeName);

        ResponseEntity<ApiResponse> resp = restTemplate.getForEntity(url, ApiResponse.class);

        if(resp.getStatusCode() == OK) {
            List<Recipe> recipes = resp.getBody().recipes;
            res = recipes
                    .stream()
                    .map(it -> {
                        SimilarRecipeDto recipe = new SimilarRecipeDto();
                        recipe.setImage(it.image);
                        recipe.setTitle(it.title);
                        return recipe;
                    })
                    .toList();
        }
        return res;
    }
}
