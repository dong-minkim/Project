package com.dutmdcjf.backendserver.model.Promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionMain {
    String event;
    List<String> rolling;
}
