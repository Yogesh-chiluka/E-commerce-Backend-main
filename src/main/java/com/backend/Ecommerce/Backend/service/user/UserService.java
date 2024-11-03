package com.backend.Ecommerce.Backend.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.backend.Ecommerce.Backend.dto.UserDto;
import com.backend.Ecommerce.Backend.exception.AlreadyExistsException;
import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.User;
import com.backend.Ecommerce.Backend.repository.UserRepository;
import com.backend.Ecommerce.Backend.request.CreateUserRequest;
import com.backend.Ecommerce.Backend.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor 
public class UserService implements IUserService{
    private final UserRepository userRepository;   
    private final ModelMapper modelMapper; 
    
    @Override
    public User getUserById(Long userId){
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public User creatUser(CreateUserRequest request){
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());

                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("Oops! " +request.getEmail()+" alredy exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId){
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public void deleteUser(Long userId){
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () ->{
            throw new ResourceNotFoundException("User not found!");
        });
    }

    @Override
    public UserDto convertUserToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
