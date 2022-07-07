package dev.danielwilliam.nxtnmbr.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import dev.danielwilliam.nxtnmbr.entity.Record;

@DataJpaTest
public class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setup() {
        Record record = Record.builder()
                .categoryCode("r1")
                .itemValue(10)
                .build();
        entityManager.persist(record);
    }

    @Test
    void whenFindByValidId_returnRecord() {
        String categoryCode = "r1";
        Record record = recordRepository.findById(categoryCode).get();
        assertEquals(10, record.getItemValue());
    }

    @Test
    void whenFindByInvalidId_returnRecord() {
        String categoryCode = "unavailable";
        assertThrows(NoSuchElementException.class, () -> {
            recordRepository.findById(categoryCode).get();
        });
    }

}