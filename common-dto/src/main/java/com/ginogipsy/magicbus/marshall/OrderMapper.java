package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Order;
import com.ginogipsy.magicbus.dto.OrderDTO;
import com.ginogipsy.magicbus.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public OrderMapper(ModelMapper modelMapper, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    public Order convertToEntity(final OrderDTO orderDTO){
        return Optional.ofNullable(orderDTO)
                .map(o -> modelMapper.map(o, Order.class))
            .orElse(null);
    }

    public OrderDTO convertToDTO(final Order order){
        return Optional.ofNullable(order)
                .map(o -> modelMapper.map(o, OrderDTO.class))
            .orElse(null);
    }
}
