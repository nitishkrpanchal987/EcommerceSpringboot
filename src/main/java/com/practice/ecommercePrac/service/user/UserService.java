package com.practice.ecommercePrac.service.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.ecommercePrac.dto.UserDto;
import com.practice.ecommercePrac.exceptions.AlreadyExistsException;
import com.practice.ecommercePrac.exceptions.ResourceNotFoundException;
import com.practice.ecommercePrac.model.User;
import com.practice.ecommercePrac.repository.UserRepository;
import com.practice.ecommercePrac.request.CreateUserRequest;
import com.practice.ecommercePrac.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request).filter(user -> !userRepository.existsByEmail(request.getEmail())).map(req -> {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            return user;
        }).orElseThrow(() -> new AlreadyExistsException("User alread exists!!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);

        }).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("user not found");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
