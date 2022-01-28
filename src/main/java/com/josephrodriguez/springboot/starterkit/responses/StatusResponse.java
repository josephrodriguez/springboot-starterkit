package com.josephrodriguez.springboot.starterkit.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponse {

    private String status;
    private LocalDateTime datetime;
}
