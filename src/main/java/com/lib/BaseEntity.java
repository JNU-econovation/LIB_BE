package com.lib;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class BaseEntity {
    //생성날짜, 수정날짜
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime updatedDate;
    private LocalDateTime isDelete;


}
