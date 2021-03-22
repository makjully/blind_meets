package web;

import dao.UserDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final PasswordEncoder encoder;

    public DetailsService(UserDAO userDAO, PasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        model.User found = userDAO.findByLogin(username);
        if (found == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return User.withUsername(username)
                .password(found.getPassword())
                .roles("USER")
                .build();
    }
}
