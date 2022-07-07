package dev.danielwilliam.nxtnmbr.service;

import org.springframework.stereotype.Service;

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
        // TODO Auto-generated method stub
        return null;
    }

}
