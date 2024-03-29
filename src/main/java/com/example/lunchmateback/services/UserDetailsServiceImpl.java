package com.example.lunchmateback.services;

import com.example.lunchmateback.dtos.MessageResponse;
import com.example.lunchmateback.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

    public ResponseEntity<?> getUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                UserDto dto = new UserDto(
                        user.get().getId(),
                        user.get().getLocked(),
                        user.get().getEnable(),
                        user.get().getUsername(),
                        user.get().getEmail(),
                        user.get().getPassword(),
                        user.get().getName(),
                        user.get().getSurname(),
                        user.get().getBirthDate()
                );
                System.out.println(dto.getUsername());
                return ResponseEntity.ok().body(dto);
            }

        }
        return ResponseEntity.badRequest().body(new MessageResponse("Nie znaleziono użytkownika."));
    }
}
