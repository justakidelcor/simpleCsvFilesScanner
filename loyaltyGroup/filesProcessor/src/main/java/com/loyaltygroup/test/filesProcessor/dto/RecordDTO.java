package com.loyaltygroup.test.filesProcessor.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loyaltygroup.test.filesProcessor.utils.CustomLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO implements Serializable {

    private static final long serialVersionUID = 1388741139843040265L;

    private Long id;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime created_when;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updateDate;
    private String transactional_id;
    private String amount;
    private String user_id;
    private String status;

}

