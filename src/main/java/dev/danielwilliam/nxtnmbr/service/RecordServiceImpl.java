package dev.danielwilliam.nxtnmbr.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.danielwilliam.nxtnmbr.entity.Record;
import dev.danielwilliam.nxtnmbr.exception.ResourceNotFoundException;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository repository;

    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseDto fetchNextNumber(String categoryCode) {
        Optional<Record> result = repository.findById(categoryCode);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        int oldValue = result.get().getItemValue();

        Record newRecord = Record.builder().categoryCode(categoryCode).itemValue(getNewValue(oldValue)).build();

        int newValue = repository.save(newRecord).getItemValue();

        return new ResponseDto(oldValue, newValue);
    }

    private int getNewValue(int oldValue) {
        // TODO:
        return 0;
    }

}
