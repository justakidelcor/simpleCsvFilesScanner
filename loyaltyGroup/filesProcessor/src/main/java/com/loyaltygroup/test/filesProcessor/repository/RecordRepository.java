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
    @Query(value = "UPDATE record SET status = :status WHERE transactional_id = :transactional_id", nativeQuery = true)
    void UpdateRecord(@Param("transactional_id") String transactional_id, @Param("status") String status);

    List<Record> getAllByUser_id(String user_id);

    List<Record> getAllByStatus(String status);

    Record getByTransactional_id(String transactional_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT DISTINCT INTO public.checked_files (file_name) VALUES (:file_name)", nativeQuery = true)
    void addCheckedFiles(@Param("file_name") String file_name);

    @Query(value = "SELECT DISTINCT FROM public.checked_files WHERE file_name = :file_name", nativeQuery = true)
    boolean checkIfFileNameIsAlreadyThere(@Param("file_name")String file_name);

}
