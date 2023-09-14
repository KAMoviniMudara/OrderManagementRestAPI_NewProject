package com.example.ordermanagementrestapi.service.impl;

import com.example.ordermanagementrestapi.dto.request.RequestOrderDetailsSaveDTO;
import com.example.ordermanagementrestapi.dto.request.RequestOrderSaveDTO;
import com.example.ordermanagementrestapi.entity.Order;
import com.example.ordermanagementrestapi.entity.OrderDetails;
import com.example.ordermanagementrestapi.repo.CustomerRepo;
import com.example.ordermanagementrestapi.repo.ItemRepo;
import com.example.ordermanagementrestapi.repo.OrderDetailsRepo;
import com.example.ordermanagementrestapi.repo.OrderRepo;
import com.example.ordermanagementrestapi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal(),
                customerRepo.getById(requestOrderSaveDTO.getCustomers())
        );
        orderRepo.save(order);

        if (orderRepo.existsById(order.getOrderID())) {
            List<OrderDetails> orderDetails = new ArrayList<>();

            orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>() {
            }.getType());
            for (int i = 0; i < orderDetails.size(); i++) {
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if (orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }

    @Override
    @Transactional
    public boolean updateOrder(int orderId, RequestOrderSaveDTO requestOrderSaveDTO) {
        if (orderRepo.existsById(orderId)) {
            Order existingOrder = orderRepo.getById(orderId);

            existingOrder.setDate(requestOrderSaveDTO.getDate());
            existingOrder.setTotal(requestOrderSaveDTO.getTotal());

            existingOrder.setCustomers(customerRepo.getById(requestOrderSaveDTO.getCustomers()));

            List<OrderDetails> updatedOrderDetails = new ArrayList<>();
            List<RequestOrderDetailsSaveDTO> requestOrderDetailsList = requestOrderSaveDTO.getOrderDetails();

            for (RequestOrderDetailsSaveDTO requestOrderDetails : requestOrderDetailsList) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setAmount(requestOrderDetails.getAmount());
                orderDetails.setItemName(requestOrderDetails.getItemName());
                orderDetails.setQty(requestOrderDetails.getQty());
                orderDetails.setOrders(existingOrder);
                orderDetails.setItems(itemRepo.getById(requestOrderDetails.getItems()));
                updatedOrderDetails.add(orderDetails);
            }

            // Clear existing order details and set the updated ones
            existingOrder.getOrderDetails().clear();
            existingOrder.getOrderDetails().addAll(updatedOrderDetails);

            orderRepo.save(existingOrder);
            return true;
        }
        return false;
    }




    @Override
    public boolean updateOrderByCustomers(RequestOrderSaveDTO requestOrderSaveDTO) {
        return false;
    }




}