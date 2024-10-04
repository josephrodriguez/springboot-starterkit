package com.josephrodriguez.springboot.starterkit.responses;

import java.time.LocalDateTime;

public class StatusResponse {

    private final String status;
    private final LocalDateTime datetime;

    public StatusResponse(String status, LocalDateTime datetime) {
        this.status = status;
        this.datetime = datetime;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getStatus() {
        return status;
    }
}
