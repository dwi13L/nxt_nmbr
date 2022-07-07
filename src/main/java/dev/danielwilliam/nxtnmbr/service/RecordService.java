package dev.danielwilliam.nxtnmbr.service;

import dev.danielwilliam.nxtnmbr.model.ResponseDto;

public interface RecordService {

    ResponseDto fetchNextNumber(String categoryCode);
}
