package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(User user) {
        if(user.getUsername().equals("validuser")){
            return true;
        }
        return false;
    }

    public void registerUser(User user) {
        userRepository.registerUser(user);
    }
}
