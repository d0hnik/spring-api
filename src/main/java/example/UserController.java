package example;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import user.User;
import user.UserDao;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/users")
    public String allUsersInfo(Principal principal) {
        return "Admin user info: " + principal.getName();
    }

    @GetMapping("/users/{username}")
    public User getUserByName(@PathVariable("username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        boolean isSameUser = username.equals(authentication.getName());

        if (isAdmin || isSameUser) {
            return new UserDao().getUserByUserName(username);
        }
        else {
            throw new AccessDeniedException("Access denied");
        }
    }
}