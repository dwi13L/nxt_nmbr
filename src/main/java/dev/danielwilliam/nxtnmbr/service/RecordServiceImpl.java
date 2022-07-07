package dev.danielwilliam.nxtnmbr.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import dev.danielwilliam.nxtnmbr.entity.Record;
import dev.danielwilliam.nxtnmbr.exception.ResourceNotFoundException;
import dev.danielwilliam.nxtnmbr.model.RecordDto;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository repository;

    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseDto fetchNextNumber(String categoryCode) throws InterruptedException {
        Optional<Record> result = repository.findById(categoryCode);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        int oldValue = result.get().getItemValue();

        Record newRecord = Record.builder().categoryCode(categoryCode).itemValue(getNewValue(oldValue)).build();

        int newValue = repository.save(newRecord).getItemValue();

        return new ResponseDto(oldValue, newValue);
    }

    /**
     * Utility methods for fetchNextNumber
     * 
     * @throws InterruptedException
     */

    private int getNewValue(int oldValue) throws InterruptedException {

        int newValue = oldValue + 1;

        for (int i = oldValue + 1;; i++) {
            int sum = recursiveSumOfDigits(i);
            if (sum == 1) {
                newValue = i;
                break;
            }
        }

        /**
         * Simulating processing time
         */

        TimeUnit.SECONDS.sleep(5);

        return newValue;
    }

    private int recursiveSumOfDigits(int num) {
        if (num == 0)
            return 0;
        return (num % 9 == 0) ? 9 : (num % 9);
    }

    @Override
    public Record createRecord(RecordDto recordDto) {

        Record record = Record.builder().categoryCode(recordDto.getCategoryCode()).itemValue(recordDto.getItemValue())
                .build();
        return repository.save(record);
    }

}
