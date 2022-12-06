package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Order;
import com.ginogipsy.magicbus.dto.OrderDTO;
import com.ginogipsy.magicbus.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

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

    public Optional<Order> convertToEntity(final Optional<OrderDTO> orderDTO){
        return Optional.ofNullable(orderDTO)
                .map(o -> modelMapper.map(o, Order.class));
    }

    public Optional<OrderDTO> convertToDTO(final Optional<Order> order){
        return Optional.ofNullable(order)
                .map(o -> modelMapper.map(o, OrderDTO.class));
    }
}
