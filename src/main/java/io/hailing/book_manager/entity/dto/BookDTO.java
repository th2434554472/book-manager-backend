package io.hailing.book_manager.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String bookName;

    private String categoryName;

    private String authorName;

    private String translator;

    private String publisherName;

    private LocalDate date;

    private BigDecimal price;

    private String image;
}
