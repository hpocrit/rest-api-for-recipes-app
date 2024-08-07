package ru.kpfu.itis.gabdullina.MyRestApi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.BaseDto;
import ru.kpfu.itis.gabdullina.MyRestApi.model.BaseModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseMapper {

    BaseDto convertToDto(BaseModel baseModel);

}
