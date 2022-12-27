package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @NonAuth
    @GetMapping("/find")
    public Object getProductFind(@RequestParam("type") String type, @RequestParam("page") Integer page, @RequestParam("count") Integer count, @RequestParam("cate1") Integer cate1, @RequestParam("cate2") Integer cate2, @RequestParam(required = false, name = "search") String search) {
        log.info("Parameter ===> Type : {}, Page : {}, Count : {}, Cate1 : {}, Cate2 : {}, Search Key word : {}", type, page, count, cate1, cate2, search);

        if("search".equals(type)) {
            return productService.getSearch(page, count, search);
        }

        return productService.getList(page, count, cate1, cate2);
    }
}
