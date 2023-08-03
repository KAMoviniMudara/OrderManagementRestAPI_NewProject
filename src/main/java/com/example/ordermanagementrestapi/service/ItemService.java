package com.example.ordermanagementrestapi.service;

import com.example.ordermanagementrestapi.dto.ItemDTO;
import com.example.ordermanagementrestapi.dto.request.RequestItemSaveDTO;

import java.util.List;

public interface ItemService {
    void addCustomer(RequestItemSaveDTO itemDTO);
    List<ItemDTO>getItemByName(String itemName);
    List<ItemDTO> getAllItems();
}
