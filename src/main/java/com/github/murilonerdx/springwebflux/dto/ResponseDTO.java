package com.github.murilonerdx.springwebflux.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ResponseDTO {
    private Date date = new Date();
    private int output;

    public ResponseDTO(int output) {
        this.output = output;
    }
}
