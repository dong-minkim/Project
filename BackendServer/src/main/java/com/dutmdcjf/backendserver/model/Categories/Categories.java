package com.dutmdcjf.backendserver.model.Categories;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Categories {
    List<Menus> bigMenu;
    List<Menus> smallMenu;
}
