package com.loyaltygroup.test.filesProcessor.services.db.impl;

import com.loyaltygroup.test.filesProcessor.dto.RecordDTO;
import com.loyaltygroup.test.filesProcessor.entity.Record;
import com.loyaltygroup.test.filesProcessor.mapper.RecordMapper;
import com.loyaltygroup.test.filesProcessor.repository.RecordRepository;
import com.loyaltygroup.test.filesProcessor.services.RecordService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RecordMapper recordMapper;

    private Record save(Record record) {
        if (record != null) {

            record.setUpdateDate(LocalDateTime.now());
            record.setUser_id(record.getUser_id());
            record.setStatus(record.getStatus());
            record.setAmount(record.getAmount());

        }

        return recordRepository.save(record);
    }

    @Override
    public List<RecordDTO> getRecordsByStatus(String status) {
        return recordMapper.convert(recordRepository.getAllByStatus(status));
    }

    @Override
    public Record addNewRecord(String transactional_id, String status, String amount, String user_id) {

        Record record = new Record();
        record.setCreated_when(LocalDateTime.now());
        record.setUpdateDate(LocalDateTime.now());
        record.setTransactional_id(transactional_id);
        record.setAmount(amount);
        record.setStatus(status);
        record.setUser_id(user_id);

        return recordRepository.save(record);

    }

    public boolean checkIfFileAlreadyThere(String file_name) {
        return recordRepository.checkIfFileNameIsAlreadyThere(file_name);
    }

    public void addNewCheckedFile(String file_name) {
         recordRepository.addCheckedFiles(file_name);
    }

    @Override
    public Record UpdateRecord(Record record) {
       return save(record);
    }

    @Override
    public Record getRecordByTransactionalId(String transactional_id) {
        return recordRepository.getByTransactional_id(transactional_id);
    }

    @Override
    public List<RecordDTO> getRecordByUserId(String user_id) {
        return recordMapper.convert(recordRepository.getAllByUser_id(user_id));
    }
}
