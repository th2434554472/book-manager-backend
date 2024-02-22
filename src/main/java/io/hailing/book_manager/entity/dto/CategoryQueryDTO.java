package io.hailing.book_manager.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryQueryDTO implements Serializable {

    //分类名称
    private String categoryName;
}
