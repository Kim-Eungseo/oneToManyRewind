package com.proxy.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String realName;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public UserDto(Long id, String username, String realName, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
