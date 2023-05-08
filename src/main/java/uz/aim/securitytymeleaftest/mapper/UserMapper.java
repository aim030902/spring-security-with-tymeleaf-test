package uz.aim.securitytymeleaftest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.aim.securitytymeleaftest.domains.entity.User;
import uz.aim.securitytymeleaftest.dtos.auth.RegisterDTO;
import uz.aim.securitytymeleaftest.dtos.user.UserDTO;

/**
 * @author : Abbosbek Murodov
 * @since : 07/05/23 / 18:04
 * Project : security-tymeleaf-test / IntelliJ IDEA
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toEntity(RegisterDTO dto);
    UserDTO toDTO(User entity);
}
