package com.max.movierating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.movierating.dto.RequestCountryDTO;
import com.max.movierating.entity.Country;
import com.max.movierating.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CountryServiceImpl countryService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER", "ADMIN"})
    void findAllByPages() throws Exception {
        // given
        String URL = "/api/v1/countries?page=0&size=2";
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country(1L, "RUSSIA"));
        countryList.add(new Country(2L, "USA"));
        Page<Country> page = new PageImpl<>(countryList);

        // when
        Mockito.when(countryService.getAllByPages(Mockito.any(PageRequest.class))).thenReturn(page);

        // then
        MvcResult mvcResult = mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(page);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER", "ADMIN"})
    void findById() throws Exception {
        // given
        Long id = 1L;
        final String URL = "/api/v1/countries/" + id;
        Country country1 = new Country(id, "RUSSIA");

        // when
        Mockito.when(countryService.findById(id)).thenReturn(country1);

        // then
        MvcResult mvcResult = mockMvc
                .perform(get(URL))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(country1);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER", "ADMIN"})
    void create() throws Exception {
        // given
        final String URL = "/api/v1/countries";
        Country newCountry = Country.builder().name("Test").build();
        Country saveCountry = new Country(1L, "Test");

        // when
        Mockito.when(countryService.save(newCountry)).thenReturn(saveCountry);

        // then
        MvcResult mvcResult = mockMvc
                .perform(post(URL)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newCountry)))
                .andExpect(status().isCreated())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(saveCountry);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER", "ADMIN"})
    void update() throws Exception {
        // given
        final Long id = 1L;
        final String URL = "/api/v1/countries/" + id;
        RequestCountryDTO countryDTO = new RequestCountryDTO();
        countryDTO.setName("Test");
        Country updatedCountry = new Country(id, "Test");

        // when
        Mockito.when(countryService.update(countryDTO.toCountry(), id)).thenReturn(updatedCountry);

        // then
        MvcResult mvcResult = mockMvc
                .perform(put(URL)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(updatedCountry);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER", "ADMIN"})
    void deleteTestWithSuccess() throws Exception {
        // given
        final Long id = 1L;
        final String URL = "/api/v1/countries/" + id;
        Country deletedCountry = new Country(id, "Test");

        // when
        Mockito.when(countryService.deleteById(1L)).thenReturn(deletedCountry);

        // then
        MvcResult mvcResult = mockMvc
                .perform(delete(URL)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(deletedCountry);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }
}