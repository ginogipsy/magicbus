package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Order;
import com.ginogipsy.magicbus.dto.OrdineDTO;
import com.ginogipsy.magicbus.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrdineMapper {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public OrdineMapper(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    public Order convertToEntity(OrdineDTO ordineDTO){
        return (ordineDTO != null) ? modelMapper.map(ordineDTO, Order.class) : null;
    }

    public OrdineDTO convertToDTO(Order order){
        return (order != null) ? modelMapper.map(order, OrdineDTO.class) : null;
    }
}
