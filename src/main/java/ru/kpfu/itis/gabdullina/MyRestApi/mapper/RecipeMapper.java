package ru.kpfu.itis.gabdullina.MyRestApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.jmx.support.RegistrationPolicy;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Recipe;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseMapper.class)
public interface RecipeMapper {

    RecipeDto convertToDto(Recipe recipe);
    Recipe convertToModel(RecipeDto recipeDto);

}
