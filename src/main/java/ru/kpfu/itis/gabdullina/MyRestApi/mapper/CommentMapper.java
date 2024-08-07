package ru.kpfu.itis.gabdullina.MyRestApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.CommentDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Comment;
import ru.kpfu.itis.gabdullina.MyRestApi.model.Recipe;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = BaseMapper.class)
public interface CommentMapper {

    CommentDto convertToDto(Comment comment);
    Comment convertToModel(CommentDto commentDto);

}
