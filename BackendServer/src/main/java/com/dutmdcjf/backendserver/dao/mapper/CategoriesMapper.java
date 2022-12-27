package com.dutmdcjf.backendserver.dao.mapper;

import com.dutmdcjf.backendserver.model.Categories.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoriesMapper {
    List<Menus> getCategories();
    List<Menus> getSubCategories();
}
