package com.dutmdcjf.backendserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private Long id;
    private Date date;
    private Date dueDate;
    private BigDecimal totalAmount;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private Integer status;
}
