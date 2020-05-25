package com.loyaltygroup.test.filesProcessor.services.db.impl;

import com.loyaltygroup.test.filesProcessor.csv.model.RecordCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class CsvReaderImpl {

    @Value("${folder.path}")
    private String srcDir;

    @Autowired
    RecordServiceImpl recordServiceImpl;

    public void readAll() throws Exception {

        File folder = new File(srcDir);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length > 0) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {

                    if (listOfFile.getName() != null
                            && !listOfFile.getName().isEmpty()
                            && !recordServiceImpl.recordRepository.checkIfFileNameIsAlreadyThere(listOfFile.getName())) {

                        recordServiceImpl.recordRepository.addCheckedFiles(listOfFile.getName());

                        Reader reader = Files.newBufferedReader(listOfFile.toPath());

                        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                                .withType(RecordCsv.class)
                                .withIgnoreLeadingWhiteSpace(true)
                                .build();

                        // iterate through users
                        for (RecordCsv recordCsv : (Iterable<RecordCsv>) csvToBean) {

                            String transactional_id = String.valueOf(recordCsv.getTransactional_id());

                            try {

                                if (recordServiceImpl.getRecordByTransactionalId(transactional_id) != null) {

                                    recordServiceImpl.UpdateRecord(recordServiceImpl.getRecordByTransactionalId(transactional_id));

                                } else {

                                    recordServiceImpl.addNewRecord(transactional_id, recordCsv.getStatus(), recordCsv.getAmount(), recordCsv.getUser_id());

                                }

                            } catch (Exception e) {

                                log.info(e.getMessage());
                                //TODO make file which produces exception marked as invalid, so it would never be checked again.

                            }
                        }

                        // close the reader
                        reader.close();

                    }
                }
            }
        }
    }
}
