package com.loyaltygroup.test.filesProcessor.services;

import com.loyaltygroup.test.filesProcessor.csv.model.RecordCsv;
import com.loyaltygroup.test.filesProcessor.dto.RecordDTO;
import com.loyaltygroup.test.filesProcessor.entity.Record;

import java.util.List;

public interface RecordService {

        List<RecordDTO> getRecordsByStatus(String status);

        Record addNewRecord(String transactional_id, String status, String amount, String user_id, String created_when);

        Record UpdateRecord(Record record, RecordCsv recordCsv);

        Record getRecordByTransactionalId(String transactional_id);

        List<RecordDTO> getRecordByUserId(String user_id);

}
