package corseproject.service;

import corseproject.domain.Role;
import corseproject.domain.User;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
    public boolean getAccess(User authUser,String username){

        if(authUser == null){
            return false;
        }
        User user = userRepository.findByUsername(username);
        if(!authUser.getUsername().equals(user.getUsername())
                && !authUser.getRoles().contains(Role.ADMIN)){
            return false;
        }
        return true;
    }
}
