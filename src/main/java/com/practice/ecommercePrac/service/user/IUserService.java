package com.practice.ecommercePrac.service.user;

import com.practice.ecommercePrac.dto.UserDto;
import com.practice.ecommercePrac.model.User;
import com.practice.ecommercePrac.request.CreateUserRequest;
import com.practice.ecommercePrac.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
