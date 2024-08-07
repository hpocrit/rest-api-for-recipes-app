package ru.kpfu.itis.gabdullina.MyRestApi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.exception.RecipeNotFoundException;
import ru.kpfu.itis.gabdullina.MyRestApi.mapper.RecipeMapper;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Recipe;
import ru.kpfu.itis.gabdullina.MyRestApi.repository.RecipeRepository;
import ru.kpfu.itis.gabdullina.MyRestApi.service.RecipeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes
                .stream()
                .map(recipeMapper::convertToDto)
                .toList();
    }

    @Override
    public RecipeDto getRecipeById(Long id) {
        return recipeMapper.convertToDto(recipeRepository.getReferenceById(id));
    }

    @Override
    public void save(RecipeDto recipe) {
        recipeRepository.save(recipeMapper.convertToModel(recipe));
    }

    @Override
    public void update(Long id, RecipeDto recipeDto) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(recipeOptional.isEmpty()) {
            throw new RecipeNotFoundException();
        }

        Recipe recipe = recipeOptional.get();
        recipe.setImage(recipeDto.getImage());
        recipe.setTitle(recipeDto.getTitle());

        recipeRepository.save(recipe);

    }

    @Override
    public void delete(Long id) {
        if(recipeRepository.findById(id).isEmpty()) {
            throw new RecipeNotFoundException();
        }
        recipeRepository.deleteById(id);
    }
}
