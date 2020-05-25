package com.loyaltygroup.test.filesProcessor.csv.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Date;

public class RecordCsv {

    @CsvBindByName
    private long transactional_id;
    @CsvBindByName
    private String amount;
    @CsvBindByName
    private String user_id;
    @CsvBindByName
    private Date created_when;
    @CsvBindByName
    private String status;


    public RecordCsv(long transactional_id, String amount, String user_id, Date created_when, String status) {
        this.transactional_id = transactional_id;
        this.amount = amount;
        this.user_id = user_id;
        this.created_when = created_when;
        this.status = status;
    }

    public long getTransactional_id() {
        return transactional_id;
    }

    public void setTransactional_id(long transactional_id) {
        this.transactional_id = transactional_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCreated_when() {
        return created_when;
    }

    public void setCreated_when(Date created_when) {
        this.created_when = created_when;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
