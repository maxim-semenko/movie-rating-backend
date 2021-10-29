package com.max.movierating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.max.movierating.dto.RegisterRequestDTO;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @InjectMocks
//    UserController userController;

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserServiceImpl userService;

    private User user1;
    private User user2;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        user1 = User.builder().firstname("first1").lastname("last1").username("username1").email("email1")
                .roles(Set.of(new Role("ROLE_USER"))).basket(new Basket()).build();

        user2 = User.builder().firstname("first2").lastname("last2").username("username2").email("email2")
                .roles(Set.of(new Role("ROLE_USER"))).basket(new Basket()).build();
    }

    @Test
    @WithMockUser(username = "max", roles = {"USER", "ADMIN"})
    void findAll() throws Exception {
        List<User> list = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(list);
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/")).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
//        String expectedResponse = objectMapper.writeValueAsString(UserDTO.fromListUser(list));

//        assertEquals(actualResponse, expectedResponse);

    }

    @Test
    @WithMockUser(username = "max", roles = {"USER", "ADMIN"})
    void findByIdWithResponseOK() throws Exception {
        when(userService.findById(1L)).thenReturn(user1);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users/1/")).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(UserDTO.fromUser(user1));

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    @WithMockUser(username = "max", roles = {"USER", "ADMIN"})
    @Rollback()
    void saveUserWithResponseCreated() throws Exception {
        RegisterRequestDTO requestDTO = RegisterRequestDTO.builder()
                .firstname("first1")
                .password("12345678")
                .lastname("last1")
                .username("username1")
                .email("email@gmail.com")
                .build();

        User savedUser = requestDTO.toUser();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(requestDTO);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", StandardCharsets.UTF_8);
        MockHttpServletRequestBuilder request = post("/api/v1/users/");
        request.content(requestJson);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        when(userService.save(requestDTO.toUser())).thenReturn(savedUser);
        mockMvc.perform(request.content(requestJson)).andExpect(status().isCreated());
    }


    @Test
    @WithMockUser(username = "max", roles = {"USER", "ADMIN"})
    @Rollback()
    void saveUserWithResponseBadRequest() throws Exception {
        User newUser = User.builder()
                .roles(Set.of(new Role("ROLE_USER"))).basket(new Basket()).build();

        User savedUser = User.builder().id(1L).roles(Set.of(new Role("ROLE_USER")))
                .basket(new Basket()).build();

        when(userService.save(newUser)).thenReturn(savedUser);
        mockMvc.perform(post("/api/v1/users/")).andExpect(status().isBadRequest());
    }

//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void findByUsername() {
//    }
}