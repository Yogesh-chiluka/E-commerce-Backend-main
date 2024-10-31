package com.backend.Ecommerce.Backend.service.user;

import com.backend.Ecommerce.Backend.dto.UserDto;
import com.backend.Ecommerce.Backend.model.User;

import com.backend.Ecommerce.Backend.request.*;

public interface IUserService {
    
    User getUserById(Long userId);
    User creatUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);  
    public UserDto convertUserToDto(User user);

}
