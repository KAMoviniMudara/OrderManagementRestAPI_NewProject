package com.example.ordermanagementrestapi.util.mappers;

import com.example.ordermanagementrestapi.dto.ItemDTO;
import com.example.ordermanagementrestapi.dto.request.RequestItemSaveDTO;
import com.example.ordermanagementrestapi.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item requestDtoToEntity(RequestItemSaveDTO requestItemSaveDTO);
    List<ItemDTO> requestEntityListToDtoList(List<Item> items);
    List<ItemDTO> retuestPagetoList(Page<Item> items);
}


