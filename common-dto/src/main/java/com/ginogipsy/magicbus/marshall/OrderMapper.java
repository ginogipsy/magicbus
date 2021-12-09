package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Order;
import com.ginogipsy.magicbus.dto.OrderDTO;
import com.ginogipsy.magicbus.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public OrderMapper(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    public Order convertToEntity(OrderDTO orderDTO){
        return (orderDTO != null) ? modelMapper.map(orderDTO, Order.class) : null;
    }

    public OrderDTO convertToDTO(Order order){
        return (order != null) ? modelMapper.map(order, OrderDTO.class) : null;
    }
}
