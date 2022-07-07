package dev.danielwilliam.nxtnmbr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.danielwilliam.nxtnmbr.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, String> {

}
