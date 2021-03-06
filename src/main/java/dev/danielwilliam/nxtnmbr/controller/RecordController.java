package dev.danielwilliam.nxtnmbr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.danielwilliam.nxtnmbr.model.RecordDto;
import dev.danielwilliam.nxtnmbr.model.ResponseDto;
import dev.danielwilliam.nxtnmbr.service.RecordService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RecordController {

    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * Message API for Landing Page
     */

    @GetMapping(value = "/")
    public String applicatoinStatus() {
        return "<h1>{status: up}</h1>";
    }

    /**
     * Required API implementation
     */

    @GetMapping(value = "/FetchNextNumber")
    public ResponseEntity<ResponseDto> fetchNextNumber(@RequestBody RecordDto record) {

        // Handling Exception inside controller for time being instead of using AOP

        try {

            ResponseDto response = recordService.fetchNextNumber(record.getCategoryCode());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            log.error(e.getMessage(), e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * API for creating records
     */

    @PostMapping(value = "/record")
    public ResponseEntity<HttpStatus> createRecord(@RequestBody RecordDto recordDto) {
        recordService.createRecord(recordDto);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

}
