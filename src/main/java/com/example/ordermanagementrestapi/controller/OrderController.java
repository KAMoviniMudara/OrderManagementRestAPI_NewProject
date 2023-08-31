package com.example.ordermanagementrestapi.controller;

import com.example.ordermanagementrestapi.dto.request.RequestOrderSaveDTO;
import com.example.ordermanagementrestapi.service.OrderService;
import com.example.ordermanagementrestapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){

        String id = orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"2 + Order saved",2), HttpStatus.OK
        );
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<StandardResponse> updateOrder(
            @PathVariable int orderId,
            @RequestBody RequestOrderSaveDTO requestOrderSaveDTO
    ) {
        boolean updated = orderService.updateOrder(orderId, requestOrderSaveDTO);
        if (updated) {
            return new ResponseEntity<>(
                    new StandardResponse(200, "Order updated successfully", null), HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new StandardResponse(404, "Order not found", null), HttpStatus.NOT_FOUND
            );
        }
    }


}

