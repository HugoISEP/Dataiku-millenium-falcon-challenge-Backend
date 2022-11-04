package com.starwars.millenniumfalcononboardcomputer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.millenniumfalcononboardcomputer.model.dto.BountyHunterDto;
import com.starwars.millenniumfalcononboardcomputer.model.dto.EmpireInformationDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MillenniumFalconControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void postMillenniumFalconOddsTest() throws Exception {
        // Given valid empire information
        val bountyHunters = List.of(new BountyHunterDto("Hoth", 6));
        val empireInformationDto = new EmpireInformationDto(7, bountyHunters);

        // When performing post method
        // Then we should get http 200 code
        mockMvc.perform(post("/")
                .content(asJsonString(empireInformationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void postMillenniumFalconOddsNullPlanetTest() throws Exception {
        // Given empire information with null planet property
        val bountyHunters = List.of(new BountyHunterDto(null, 6));
        val empireInformationDto = new EmpireInformationDto(7, bountyHunters);

        // When performing post method
        // Then we should get a 400 http error code
        mockMvc.perform(post("/")
                        .content(asJsonString(empireInformationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().is4xxClientError());
    }

    @Test
    void postMillenniumFalconOddsEmptyPlanetTest() throws Exception {
        // Given empire information with empty planet property
        val bountyHunters = List.of(new BountyHunterDto("", 6));
        val empireInformationDto = new EmpireInformationDto(7, bountyHunters);

        // When performing post method
        // Then we should get a 400 http error code
        mockMvc.perform(post("/")
                        .content(asJsonString(empireInformationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().is4xxClientError());
    }

    @Test
    void postMillenniumFalconOddsNegativeCountdownTest() throws Exception {
        // Given empire information with negative countdown property
        val bountyHunters = List.of(new BountyHunterDto("Hoth", 6));

        // When performing post method
        // Then we should get a 400 http error code
        val empireInformationDto = new EmpireInformationDto(-1, bountyHunters);
        mockMvc.perform(post("/")
                        .content(asJsonString(empireInformationDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).
                andExpect(status().is4xxClientError());
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
