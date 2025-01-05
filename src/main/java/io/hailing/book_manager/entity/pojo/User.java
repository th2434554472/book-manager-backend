package io.hailing.book_manager.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
