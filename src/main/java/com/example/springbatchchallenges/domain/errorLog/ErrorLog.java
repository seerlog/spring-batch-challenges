package com.example.springbatchchallenges.domain.errorLog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ErrorLog {
    @Id
    @Column(name = "NO", nullable = false)
    private Long no;

    @Column(name = "CONTENT")
    private String content;

    public static ErrorLog of(Long no, String content) {
        return new ErrorLog(no, content);
    }
}
