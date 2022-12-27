package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.model.Promotion.PromotionList;
import com.dutmdcjf.backendserver.model.Promotion.PromotionMain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionService {

    public PromotionMain getMainPromotion() {
        return new PromotionMain(null, "", null);
    }

    public PromotionList getServicePromotion() {
        return new PromotionList(null, null);
    }
}
