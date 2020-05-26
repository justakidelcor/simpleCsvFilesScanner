package com.loyaltygroup.test.filesProcessor.csv.model;

import com.opencsv.bean.CsvBindByName;

public class TestCell {
    @CsvBindByName(column = "transactional_id", required = true)
    private String transactional_id;
    /*@CsvBindByName(column = "amount", required = true)
    private String amount;
    @CsvBindByName(column = "user_id", required = true)
    private String user_id;
    @CsvBindByName(column = "created_when", required = true)
    private String created_when;
    @CsvBindByName(column = "status", required = true)
    private String status;*/

    public TestCell() {
    }

    public TestCell(String transactional_id) {
        this.transactional_id = transactional_id;
        /*this.amount = amount;
        this.user_id = user_id;
        this.created_when = created_when;
        this.status = status;*/
    }

    public String getTransactional_id() {
        return transactional_id;
    }

    public void setTransactional_id(String transactional_id) {
        this.transactional_id = transactional_id;
    }

    /*public String getAmount() {
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

    public String getCreated_when() {
        return created_when;
    }

    public void setCreated_when(String created_when) {
        this.created_when = created_when;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    @Override
    public String toString() {
        return "{" + transactional_id + "}";
    }
}
