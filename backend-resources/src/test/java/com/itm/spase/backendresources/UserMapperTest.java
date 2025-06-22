package com.itm.spase.backendresources;

import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    /**
     * Тест маппинга UserRepresentation в UserResponse.
     * Проверяет корректность преобразования всех полей, включая роли и группы.
     */
    @Test
    void userRepresentationToUserResponse() {
        // Подготовка тестовых данных
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName("John");
        userRepresentation.setLastName("Doe");
        userRepresentation.setEmail("john.doe@example.com");

        RoleRepresentation role = new RoleRepresentation();
        role.setName("user");
        List<RoleRepresentation> roles = List.of(role);

        GroupRepresentation group = new GroupRepresentation();
        group.setName("test-group");
        List<GroupRepresentation> groups = List.of(group);

        // Вызов метода маппинга
        UserResponse response = userMapper.userRepresentationToUserResponse(
                userRepresentation, roles, groups);

        // Проверки
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("john.doe@example.com", response.getEmail());
        assertEquals(1, response.getRoles().size());
        assertEquals("user", response.getRoles().get(0));
        assertEquals(1, response.getGroups().size());
        assertEquals("test-group", response.getGroups().get(0));
    }
}