package dev.danielwilliam.nxtnmbr.service;

import dev.danielwilliam.nxtnmbr.model.RecordDto;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.entity.Record;

public interface RecordService {

    ResponseDto fetchNextNumber(String categoryCode) throws InterruptedException;

    Record createRecord(RecordDto recordDto);
}
