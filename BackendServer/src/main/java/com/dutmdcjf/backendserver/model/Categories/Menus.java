package com.dutmdcjf.backendserver.model.Categories;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menus {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long cate2Id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long cate1Id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String cateName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String cateName2;
}
