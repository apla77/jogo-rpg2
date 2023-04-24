package com.apirest.jogorpg.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMassage {
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
