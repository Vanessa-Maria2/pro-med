package br.edu.ufrn.promed.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    LocalDateTime timestamp;

    Integer code;

    String status;

    List<String> errors;

    public ApiError(LocalDateTime now, Integer code, String status, List<String> errors) {
        this.timestamp = now;
        this.code = code;
        this.status = status;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public Integer getCode() {
        return code;
    }



    public String getStatus() {
        return status;
    }


    public List<String> getErrors() {
        return errors;
    }
}
