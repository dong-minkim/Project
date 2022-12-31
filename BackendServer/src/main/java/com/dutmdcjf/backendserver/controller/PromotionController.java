package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.model.Promotion.PromotionList;
import com.dutmdcjf.backendserver.model.Promotion.PromotionMain;
import com.dutmdcjf.backendserver.service.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    @NonAuth
    @GetMapping("/main")
    public PromotionMain getMainPromotion() throws Exception {
        return promotionService.getMainPromotion();
    }

    @NonAuth
    @GetMapping("/service")
    public PromotionList getServicePromotion() throws Exception {
        return promotionService.getServicePromotion();
    }
}
