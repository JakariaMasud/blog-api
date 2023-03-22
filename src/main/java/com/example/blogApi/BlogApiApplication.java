package com.example.blogApi;

import com.example.blogApi.config.AppConstants;
import com.example.blogApi.entity.Role;
import com.example.blogApi.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogApiApplication  implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            Role normalUser = new Role(AppConstants.NORMAL_USER,"ROLE_NORMAL");
            Role adminUser = new Role(AppConstants.ADMIN_USER,"ROLE_ADMIN");
            List<Role> roles = List.of(normalUser,adminUser);
            roleRepository.saveAll(roles);
        }catch (Exception e){
            System.out.println("Exception occurred during role initialization");
        }

    }
}
