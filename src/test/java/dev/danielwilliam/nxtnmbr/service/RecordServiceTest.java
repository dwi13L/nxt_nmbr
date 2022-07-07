package dev.danielwilliam.nxtnmbr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dev.danielwilliam.nxtnmbr.entity.Record;
import dev.danielwilliam.nxtnmbr.exception.ResourceNotFoundException;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.repository.RecordRepository;;

@SpringBootTest
public class RecordServiceTest {

    @Autowired
    private RecordService recordService;

    @MockBean
    private RecordRepository recordRepository;

    @BeforeEach
    void setUp() {

        Record oldRecord = Record.builder()
                .categoryCode("r1")
                .itemValue(10)
                .build();

        Optional<Record> result = Optional.ofNullable(oldRecord);

        Record newRecord = Record.builder()
                .categoryCode("r1")
                .itemValue(19)
                .build();

        Mockito.when(recordRepository.findById("r1")).thenReturn(result);

        Mockito.when(recordRepository.save(newRecord)).thenReturn(newRecord);

    }

    @Test
    // @DisplayName("Get Data for Valid Input")
    void whenFetchNextNumberCalledWithValidCategoryCode_thenReturnReponseDto() throws InterruptedException {
        String categoryCode = "r1";
        ResponseDto response = recordService.fetchNextNumber(categoryCode);
        assertEquals(10, response.getOldValue());
        assertEquals(19, response.getNewValue());
    }

    @Test
    // @DisplayName("Throw Error for Invalid Input")
    void whenFetchNextNumberCalledWithInvalidCategoryCode_thenThrowResourceNotFoundException() {
        String categoryCode = "unavailable";
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            recordService.fetchNextNumber(categoryCode);
        });

        assertEquals(ResourceNotFoundException.class, ex.getClass());
        assertEquals("RESOURCE_NOT_FOUND", ex.getMessage());
    }
}
