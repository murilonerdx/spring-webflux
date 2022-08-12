package com.github.murilonerdx.springwebflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Date date = new Date();
    private int output;

    public ResponseDTO(int output) {
        this.output = output;
    }
}
