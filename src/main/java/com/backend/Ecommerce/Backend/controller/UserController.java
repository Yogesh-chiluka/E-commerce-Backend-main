package com.backend.Ecommerce.Backend.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.Ecommerce.Backend.dto.UserDto;
import com.backend.Ecommerce.Backend.exception.AlreadyExistsException;
import com.backend.Ecommerce.Backend.exception.ResourceNotFoundException;
import com.backend.Ecommerce.Backend.model.User;

import com.backend.Ecommerce.Backend.request.CreateUserRequest;
import com.backend.Ecommerce.Backend.request.UserUpdateRequest;
import com.backend.Ecommerce.Backend.response.ApiResponse;
import com.backend.Ecommerce.Backend.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;


    @GetMapping("/{userId}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable  Long userId){
        try {
            User user = userService.getUserById(userId);
            UserDto userDto = userService.convertUserToDto(user);
;            return ResponseEntity.ok(new ApiResponse("Succes", userDto));
        } catch (ResourceNotFoundException  e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("User not found", e.getMessage()));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> creatUser(@RequestBody CreateUserRequest request){
        try {
            User user = userService.creatUser(request);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User successfully created", userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }
    
    @PutMapping("/{userId}/updateUser")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request,@PathVariable Long userId){

        try {
            User user = userService.updateUser(request, userId);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("User data successfully updated", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @DeleteMapping("/{userId}/deleteUser")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User successfully deleted",null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    
}
