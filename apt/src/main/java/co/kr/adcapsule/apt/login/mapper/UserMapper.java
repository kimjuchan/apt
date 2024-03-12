package co.kr.adcapsule.apt.login.mapper;


import co.kr.adcapsule.apt.login.domain.User;
import co.kr.adcapsule.apt.login.dto.UserCreateDto;
import co.kr.adcapsule.apt.login.dto.UserDto;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//Mapping 하지 않은 값에 대한 오류 넘김 처리를 위해서 IGNORE 처리.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    //Mapper 설정.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //MemberCreateRequest TO MembersDto Type 변환.
    User UserCreateRequestToEntity(UserCreateDto userCreateDto);

    //Member TO MembersDto Type 변환
    UserDto UserToUserDto(User user);

}
