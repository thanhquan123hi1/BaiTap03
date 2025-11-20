package vn.Quan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryModel {

    private int cateId;
    private String cateName;
    private String icons;

    private UserModel user;
}