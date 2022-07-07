package dev.danielwilliam.nxtnmbr.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.danielwilliam.nxtnmbr.exception.ResourceNotFoundException;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.service.RecordService;

@WebMvcTest(RecordController.class)
public class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Test
    void whenFetchNextNumberCalledWithValidInput_returnCorrectReponseJson() throws Exception {
        ResponseDto responseDto = ResponseDto.builder()
                .OldValue(10)
                .NewValue(19)
                .build();

        Mockito.when(recordService.fetchNextNumber("r1")).thenReturn(responseDto);

        String requestBody = "{\"categoryCode\":\"r1\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/FetchNextNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.oldValue").value("10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.newValue").value("19"));
    }

    @Test
    void whenFetchNextNumberCalledWithInvalidInput_returnBadRequestStatus() throws Exception {

        Mockito.when(recordService.fetchNextNumber("r10"))
                .thenThrow(ResourceNotFoundException.class);

        String requestBody = "{\"categoryCode\":\"r10\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/FetchNextNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
