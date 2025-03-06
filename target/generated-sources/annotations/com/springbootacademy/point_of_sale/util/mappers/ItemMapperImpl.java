package com.springbootacademy.point_of_sale.util.mappers;

import com.springbootacademy.point_of_sale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.point_of_sale.entity.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T15:31:29+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public List<ItemGetResponseDTO> entityListToDtoList(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemGetResponseDTO> list = new ArrayList<ItemGetResponseDTO>( items.size() );
        for ( Item item : items ) {
            list.add( itemToItemGetResponseDTO( item ) );
        }

        return list;
    }

    @Override
    public List<ItemGetResponseDTO> itemlistToDTOList(Page<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemGetResponseDTO> list = new ArrayList<ItemGetResponseDTO>();
        for ( Item item : items ) {
            list.add( itemToItemGetResponseDTO( item ) );
        }

        return list;
    }

    protected ItemGetResponseDTO itemToItemGetResponseDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemGetResponseDTO itemGetResponseDTO = new ItemGetResponseDTO();

        itemGetResponseDTO.setId( item.getId() );
        itemGetResponseDTO.setItemName( item.getItemName() );
        itemGetResponseDTO.setBalanceQty( item.getBalanceQty() );
        itemGetResponseDTO.setSupplierPrice( item.getSupplierPrice() );
        itemGetResponseDTO.setSellingPrice( item.getSellingPrice() );
        if ( item.getActivestate() != null ) {
            itemGetResponseDTO.setActivestate( item.getActivestate() );
        }

        return itemGetResponseDTO;
    }
}
