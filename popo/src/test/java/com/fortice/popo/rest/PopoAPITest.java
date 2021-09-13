package com.fortice.popo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.popo.dto.OptionDTO;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@NoArgsConstructor
//@Slf4j
@SpringBootTest
public class PopoAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PopoCreateRequest request;

    @BeforeEach
    public void beforeEach() {
        objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();
        OptionDTO option = OptionDTO.builder()
                .name("테스트")
                .order(1)
                .build();
        List<OptionDTO> optionParams = new ArrayList<>();
        optionParams.add(option);
        PopoCreateRequest request = PopoCreateRequest.builder()
                .category(1)
                .order(3)
                .options(optionParams)
                .build();
        this.request = request;
    }

    @Test
    public void getListPopoTest() throws Exception {
        String url = "/popo";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    System.out.println(response.getContentAsString());
                    //log.info("test");
                });
    }

    @Test
    public void insertAndDeletePopoTest() throws Exception{
        String url = "/popo";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    System.out.println(response.getContentAsString());
                    //log.info(response.getContentAsString());
                });
    }
}
