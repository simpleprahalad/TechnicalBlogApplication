package technicalblog.service;

import org.springframework.stereotype.Service;
import technicalblog.model.User;

@Service
public class UserService {
    public boolean login(User user) {
        if(user.getUserName().equals("validuser")){
            return true;
        }
        return false;
    }
}
