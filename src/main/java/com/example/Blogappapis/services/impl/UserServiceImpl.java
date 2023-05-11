package com.example.Blogappapis.services.impl;

import com.example.Blogappapis.entities.User;
import com.example.Blogappapis.excepetions.ResourceNotFoundException;
import com.example.Blogappapis.payloads.UserDto;
import com.example.Blogappapis.repositories.UserRepo;
import com.example.Blogappapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user= this.dtotoUser(userDto);
        User SavedUser = this.userRepo.save(user);
        UserDto convertedUser = this.usertoDto(SavedUser);
        return convertedUser;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.usertoDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        UserDto fetchedUser = this.usertoDto(user);
        return fetchedUser;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtotoUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto usertoDto(User user){
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
