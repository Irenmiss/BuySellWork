package ru.skypro.homework.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

    @Service
    @Data
    public class MyUserDetailsService implements UserDetailsService {
        private final UsersRepository usersRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = usersRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return new MyUserPrincipal();
        }
    }
