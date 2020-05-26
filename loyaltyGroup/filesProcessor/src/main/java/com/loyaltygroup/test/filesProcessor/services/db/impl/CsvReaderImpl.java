package com.loyaltygroup.test.filesProcessor.services.db.impl;

import com.loyaltygroup.test.filesProcessor.csv.model.RecordCsv;
import com.loyaltygroup.test.filesProcessor.entity.Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class CsvReaderImpl {

    @Value("${folder.path}")
    private String srcDir;

    @Autowired
    RecordServiceImpl recordServiceImpl;

    public void readAll() {

        File folder = new File(srcDir);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length > 0) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {

                    if (listOfFile.getName() != null
                            && !listOfFile.getName().isEmpty()
                            && !recordServiceImpl.checkIfFileAlreadyThere(listOfFile.getName())) {

                        recordServiceImpl.addNewCheckedFile(listOfFile.getName());

                        List<RecordCsv> currentFileParsed = parseCSVWithHeader(listOfFile);

                        currentFileParsed.forEach(recordCsv -> {

                            String transactional_id = String.valueOf(recordCsv.getTransactional_id());

                            try {

                                if (recordServiceImpl.getRecordByTransactionalId(transactional_id) != null) {

                                    recordServiceImpl.UpdateRecord(recordServiceImpl.getRecordByTransactionalId(transactional_id), recordCsv);

                                } else {

                                    recordServiceImpl.addNewRecord(transactional_id, recordCsv.getStatus(), recordCsv.getAmount(), recordCsv.getUser_id(), recordCsv.getCreated_when());

                                }

                            } catch (Exception e) {

                                log.info(e.getMessage());

                            }
                        });
                    }
                }
            }
        }
    }

    private static List<RecordCsv> parseCSVWithHeader(File file) {

        List<RecordCsv> emps = new ArrayList<>();

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            CsvToBeanBuilder<RecordCsv> beanBuilder = new CsvToBeanBuilder<RecordCsv>(csvReader);

            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader)
                    .withSkipLines(1)
                    .withType(RecordCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<RecordCsv> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                RecordCsv csvRecord = csvUserIterator.next();
                emps.add(csvRecord);
                System.out.println("transactional_id : " + csvRecord.getTransactional_id());
                System.out.println("Amount : " + csvRecord.getAmount());
                System.out.println("created_when : " + csvRecord.getCreated_when());
                System.out.println("Status : " + csvRecord.getStatus());
                System.out.println("user_id : " + csvRecord.getUser_id());
                System.out.println("==========================");
            }
        } catch (IOException ex) {
            log.info(Arrays.toString(ex.getStackTrace()));
            log.error(ex.getMessage(), ex);
        }

        emps.remove(0);

        return emps;

    }
}
