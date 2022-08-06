package com.github.murilonerdx.springwebflux.service;

import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.util.SleepUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public ResponseDTO findSquare(int input) {
        return new ResponseDTO(input * input);
    }

    public List<ResponseDTO> multiplicationTable(int input) {
        return IntStream.rangeClosed(1, 100)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> LOGGER.info("math-service procesing : " + i))
                .mapToObj(i -> new ResponseDTO(i * input))
                .collect(Collectors.toList());
    }
}
