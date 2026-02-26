package com.example.amazonlike.service;

import com.example.amazonlike.dto.OrderDTO;
import com.example.amazonlike.exception.ResourceNotFoundException;
import com.example.amazonlike.model.Order;
import com.example.amazonlike.model.Product;
import com.example.amazonlike.model.User;
import com.example.amazonlike.repository.OrderRepository;
import com.example.amazonlike.repository.ProductRepository;
import com.example.amazonlike.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository,
                        ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return mapToDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + orderDTO.getUserId()));
        Product product = productRepository.findById(orderDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + orderDTO.getProductId()));

        if (product.getStock() < orderDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock for product id " + product.getId());
        }

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(product.getPrice() * orderDTO.getQuantity());
        order.setOrderDate(LocalDateTime.now());

        // Deduct stock
        product.setStock(product.getStock() - orderDTO.getQuantity());
        productRepository.save(product);

        Order saved = orderRepository.save(order);
        return mapToDTO(saved);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        existing.setQuantity(orderDTO.getQuantity());
        existing.setTotalPrice(existing.getProduct().getPrice() * orderDTO.getQuantity());
        Order updated = orderRepository.save(existing);
        return mapToDTO(updated);
    }

    public void deleteOrder(Long id) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        orderRepository.delete(existing);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setProductId(order.getProduct().getId());
        dto.setQuantity(order.getQuantity());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
