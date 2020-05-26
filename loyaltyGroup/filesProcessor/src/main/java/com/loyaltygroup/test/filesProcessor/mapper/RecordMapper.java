package com.loyaltygroup.test.filesProcessor.mapper;

import com.loyaltygroup.test.filesProcessor.dto.RecordDTO;
import com.loyaltygroup.test.filesProcessor.entity.Record;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class RecordMapper implements Mapper<Record, RecordDTO> {

        @Override
        public Record revert(RecordDTO dto, Supplier<Record> factory) {
            if (dto == null) {
                return null;
            }
            Record record = factory.get();
            record.setId(dto.getId());
            record.setCreated_when(dto.getCreated_when());
            record.setTransactionalId(dto.getTransactional_id());
            record.setAmount(dto.getAmount());
            record.setStatus(dto.getStatus());
            record.setUserId(dto.getUser_id());
            record.setUpdateDate(dto.getUpdateDate());

            return record;
        }

        @Override
        public Record revert(RecordDTO dto) {
            return this.revert(dto, Record::new);
        }

        @Override
        public RecordDTO convert(Record entity, Supplier<RecordDTO> factory) {
            if (entity == null) {
                return null;
            }
            RecordDTO recordDTO = factory.get();
            recordDTO.setId(entity.getId());
            recordDTO.setCreated_when(entity.getCreated_when());
            recordDTO.setTransactional_id(entity.getTransactionalId());
            recordDTO.setAmount(entity.getAmount());
            recordDTO.setStatus(entity.getStatus());
            recordDTO.setUser_id(entity.getUserId());
            recordDTO.setUpdateDate(entity.getUpdateDate());
            return recordDTO;
        }

        @Override
        public RecordDTO convert(Record entity) {
            return this.convert(entity, RecordDTO::new);
        }

}
