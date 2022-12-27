package com.dutmdcjf.backendserver.model.Promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    Integer cols;
    String title;
    String description;
    Integer bigmenu;
    String bigmenuName;
    Integer smallmenu;
    String url;
    String image;
}
