package corseproject.service;

import corseproject.domain.Role;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import corseproject.repos.CommentRepository;
import corseproject.repos.RatingRepository;
import corseproject.repos.TShirtRepository;
import corseproject.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TShirtRepository tShirtRepository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private CommentRepository commentRepository;

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

    public void block(User user){
        user.setActive(false);
        userRepository.save(user);
    }

    public void unblock(User user){
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
    }

    public void delete(User user){
        commentRepository.deleteByAuthor(user);
        ratingRepository.deleteByAuthor(user);
        List<TShirt> tShirts = tShirtRepository.findByAuthor(user);
        User admin = userRepository.findByUsername("admin");
        for (TShirt tShirt: tShirts) {
            tShirt.setAuthor(admin);
            tShirtRepository.save(tShirt);
        }
        userRepository.delete(user);
    }

    public void save(Map<String, String> form, User user){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for(String key:form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public boolean addUser(User user){
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to TShirts, Please, visit new link:"+
                        "http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(), "Activation code", message);
        try {
            userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
