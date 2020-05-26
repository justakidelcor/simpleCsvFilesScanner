package com.loyaltygroup.test.filesProcessor.repository;

import com.loyaltygroup.test.filesProcessor.entity.Record;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE record SET status = ?1 WHERE transactional_id = ?2", nativeQuery = true)
    void UpdateRecord(@Param("transactional_id") String transactional_id, @Param("status") String status);

    List<Record> findAllByUserId(@Param("user_id")String user_id);

    List<Record> getAllByStatus(@Param("status") String status);

    Record getByTransactionalId(@Param("transactional_id") String transactional_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO checked_files (file_name) VALUES (?1)", nativeQuery = true)
    void addCheckedFiles(@Param("file_name") String file_name);

    @Query(value = "select * from public.checked_files where file_name = ?1", nativeQuery = true)
    String checkIfFileNameIsAlreadyThere(@Param("file_name") String file_name);

}
