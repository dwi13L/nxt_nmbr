package dev.danielwilliam.nxtnmbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.danielwilliam.nxtnmbr.entity.Record;

public interface RecordRepository extends JpaRepository<Record, String> {

}
