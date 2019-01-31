package sda.java17.zgagamacservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sda.java17.zgagamacservice.component.DefaultUserCreator;
import sda.java17.zgagamacservice.model.AppUser;

import java.util.Optional;

@Service(value = "loginService1")
public class LoginService implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private DefaultUserCreator defaultUserCreator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserService.findByEmail(email);
        if (appUserOptional.isPresent()) {
            // poprawne logowanie
            AppUser appUser = appUserOptional.get();

            if (appUser.getEmail().equals("admin@localhost")) {
                return User.withUsername(appUser.getEmail()).password(appUser.getPassword()).roles("ADMIN", "USER").build();
            }
            return User.withUsername(appUser.getEmail()).password(appUser.getPassword()).roles("USER").build();
        }
        // brak użytkownika z podanym mailem!
        //
        throw new UsernameNotFoundException("User not found by name: " + email);
    }

    public Optional<AppUser> getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            // nie jesteśmy zalogowani
            return Optional.empty();
        }

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return appUserService.findByEmail(user.getUsername());
            // jesteśmy zalogowani, zwracamy user'a
        }

        return Optional.empty();
    }
}
