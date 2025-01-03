package com.example.planner.service.user;

import com.example.planner.config.PasswordEncoder;
import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.dto.user.UserUpdateUserIdRequestDto;
import com.example.planner.dto.user.UserUpdatePasswordRequestDto;
import com.example.planner.dto.user.UserResponseDto;
import com.example.planner.exception.PlanNotFoundException;
import com.example.planner.model.User;
import com.example.planner.exception.UserNotFoundException;
import com.example.planner.repository.user.UserRepository;
import com.example.planner.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        return UserMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUser() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException(ErrorMessage.USER_NOT_FOUND);
        }
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updatePassword(Long id, Long sessionUserId, UserUpdatePasswordRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        // entity 메서드로 변경 isOwner
        if (!user.getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        user.updatePassword(requestDto.getPassword(), passwordEncoder);

        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUserName(Long id, Long sessionUserId, UserUpdateUserIdRequestDto requestDto) {
       if(!id.equals(sessionUserId)){
            throw new AuthenticationException(ErrorMessage.USER_LOGGED_OUT);
       }

        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        // entity 메서드로 변경 isOwner?
        if (!user.getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        user.updateUserName(requestDto.getUserName());

        return UserMapper.toDto(user);
    }

    @Override
    public void deleteEmail(Long id, Long sessionUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        // entity 메서드로 변경 isOwner?
        if (!user.getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        userRepository.delete(user);
    }

    @Override
    public void checkDuplicateEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new AuthenticationException(ErrorMessage.DUPLICATE_EMAIL);
        }
    }

    @Override
    public User findUserByEmailOrThrow(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException(ErrorMessage.EMAIL_NOT_FOUND));
    }

    @Override
    public User findUserByUserIdOrThrow(Long sessionUserId){
         return userRepository.findById(sessionUserId)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findById(Long sessionUserId) {
        return userRepository.findById(sessionUserId)
                .orElseThrow(()-> new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND));
    }
}
