package br.com.olindaweb.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public UserModel creat(@RequestBody UserModel user){
        var userCreated = this.userRepository.save(user);

        return userCreated;
    }
}
