package br.com.olindaweb.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity creat(@RequestBody UserModel user){
        var users = this.userRepository.findByUsername(user.getUsername());
        if (users != null){
            System.out.println("Usuario já existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        
        var passwordHashered = BCrypt.withDefaults()
            .hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passwordHashered);

        var userCreated = this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
