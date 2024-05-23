package nashtech.khanhdu.backend.mapper;

import nashtech.khanhdu.backend.dto.UserDto;
import nashtech.khanhdu.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    UserDto toDto (User user);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "productsRating", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "productsRating", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User updateUser (@MappingTarget User user, UserDto dto);
}
