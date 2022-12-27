package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.model.Categories.Categories;
import com.dutmdcjf.backendserver.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gategories")
public class CategoryController {
    private final CategoryService categoryService;

    @CrossOrigin(origins = "*")
    @NonAuth
    @GetMapping("/menus")
    public Categories getMenus() {
        return categoryService.getMenu();
    }
}
