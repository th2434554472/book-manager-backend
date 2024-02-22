package io.hailing.book_manager.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookName;

    private LocalDate date;

    private String authorName;

    private String translator;

    private BigDecimal price;

    private String publisherName;

    private String categoryName;

    private String image;
}
