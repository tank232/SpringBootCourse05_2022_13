package com.av.controller;

import com.av.db.User;
import com.av.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class BookControllerTest {
    @Autowired
    private MockMvc mock;
    @Autowired
    UserRepository userRepository;

    @Test
    void getBooks302() throws Exception {
        mock.perform( MockMvcRequestBuilders.get("/book/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMovedTemporarily());
    }


    @Test
    @WithMockUser("user")
    void getBooksOk() throws Exception {
        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        userRepository.save(user);
        mock.perform( MockMvcRequestBuilders.get("/book/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}