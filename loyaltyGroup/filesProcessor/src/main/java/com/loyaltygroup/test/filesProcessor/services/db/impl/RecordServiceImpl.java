package com.loyaltygroup.test.filesProcessor.services.db.impl;

import com.loyaltygroup.test.filesProcessor.csv.model.RecordCsv;
import com.loyaltygroup.test.filesProcessor.dto.RecordDTO;
import com.loyaltygroup.test.filesProcessor.entity.Record;
import com.loyaltygroup.test.filesProcessor.mapper.RecordMapper;
import com.loyaltygroup.test.filesProcessor.repository.RecordRepository;
import com.loyaltygroup.test.filesProcessor.services.RecordService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RecordMapper recordMapper;

    private Record save(Record oldRecord, RecordCsv recordCsv) {

        if (oldRecord != null) {

            oldRecord.setUpdateDate(LocalDateTime.now());
            oldRecord.setUserId(recordCsv.getUser_id());
            oldRecord.setStatus(recordCsv.getStatus());
            oldRecord.setAmount(recordCsv.getAmount());

        }

        return recordRepository.save(oldRecord);
    }

    @Override
    public List<RecordDTO> getRecordsByStatus(String status) {
        return recordMapper.convert(recordRepository.getAllByStatus(status));
    }

    @SneakyThrows
    @Override
    public Record addNewRecord(String transactional_id, String status, String amount, String user_id, String created_when) {

        Record record = new Record();
        Date dateTime = new SimpleDateFormat("dd.MM.yyyy").parse(created_when);
        LocalDateTime createdDate = LocalDateTime.ofInstant(dateTime.toInstant(),
                ZoneId.systemDefault());
        record.setCreated_when(createdDate);
        record.setUpdateDate(LocalDateTime.now());
        record.setTransactionalId(transactional_id);
        record.setAmount(amount);
        record.setStatus(status);
        record.setUserId(user_id);

        return recordRepository.save(record);

    }

    public boolean checkIfFileAlreadyThere(String file_name) {
        String foundFiles = recordRepository.checkIfFileNameIsAlreadyThere(file_name);

        return foundFiles != null;
    }

    public void addNewCheckedFile(String file_name) {

        recordRepository.addCheckedFiles(file_name);
    }

    @Override
    public Record UpdateRecord(Record record, RecordCsv recordCsv) {
        return save(record, recordCsv);
    }

    @Override
    public Record getRecordByTransactionalId(String transactional_id) {
        return recordRepository.getByTransactionalId(transactional_id);
    }

    @Override
    public List<RecordDTO> getRecordByUserId(String user_id) {
        return recordMapper.convert(recordRepository.findAllByUserId(user_id));
    }
}
