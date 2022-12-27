package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.model.Categories.Categories;
import com.dutmdcjf.backendserver.model.Categories.Menus;
import com.dutmdcjf.backendserver.dao.mapper.CategoriesMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoriesMapper categoriesMapper;

    public Categories getMenu() {
        List<Menus> categorieslist = categoriesMapper.getCategories();
        List<Menus> subCategoriesList = categoriesMapper.getSubCategories();

        return new Categories(categorieslist, subCategoriesList);
    }
}
