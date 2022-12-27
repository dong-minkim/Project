package com.dutmdcjf.backendserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInvoice {
    private Integer invoiceType;
    private String startDate;
    private String endDate;
}
