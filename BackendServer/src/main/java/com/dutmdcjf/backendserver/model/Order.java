package com.dutmdcjf.backendserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("order_id")
    Long orderIdx;
    @JsonProperty("customer_id")
    Long accountIdx;
    @JsonProperty("complete_date")
    Date completeDate;
    @JsonProperty("checkout_date")
    Date checkoutDate;
    @JsonProperty("order_status")
    Integer orderStatus;
    @JsonProperty("pur_id")
    String purId;
    @JsonProperty("inv_no")
    Long invNo;
    @JsonProperty("issue_date")
    Date issueDate;
    Date date;
    String remark;
    Integer done;
    @JsonProperty("item_cnt")
    Integer itemCnt;
    @JsonProperty("total_amount")
    BigDecimal totalAmount;
}
