package com.micropos.counter.web;

import com.micropos.counter.model.Item;
import com.micropos.products.api.CounterApi;
import com.micropos.products.dto.CounterProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api")
public class CounterResource implements CounterApi {

    @RequestMapping(value = "counter", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<BigDecimal> counterSettlement(@Valid List<CounterProductDto> items) {
        double total = 0.0;
        for (var item: items) {
            total += item.getPrice().doubleValue() * item.getQuantity();
        }
        return new ResponseEntity<>(new BigDecimal(total), HttpStatus.OK);
    }

}
