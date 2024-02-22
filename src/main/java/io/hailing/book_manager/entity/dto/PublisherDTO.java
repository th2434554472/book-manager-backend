package io.hailing.book_manager.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublisherDTO implements Serializable {
    private String name;

    private String address;

    private String website;

    private String phone;

    private String email;

    private String code;

}
