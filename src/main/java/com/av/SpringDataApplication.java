package com.av;

import com.av.db.Role;
import com.av.db.User;
import com.av.repository.RoleRepository;
import com.av.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SpringDataApplication {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            Role role=new Role();
            role.setName("USER");
            roleRepository.save(role);
            User user = new User();
            user.setLogin("user");
            user.setPassword("user");
            user.getRole().add(role);
            userRepository.save(user);

            Role adminRole=new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.getRole().add(adminRole);
            userRepository.save(admin);
        };
    }
}
