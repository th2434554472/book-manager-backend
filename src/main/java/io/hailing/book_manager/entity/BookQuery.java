package io.hailing.book_manager.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookQuery implements Serializable {
    private String bookName;

    private String authorName;

    private String publisherName;

    private String categoryName;
}
