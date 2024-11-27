package example;

import jakarta.validation.Valid;
import models.Order;
import models.OrderDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class OrderController {

    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Valid Order order) {
        return orderDao.save(order);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderDao.findAll();
    }

    @GetMapping("orders/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        return orderDao.findById(id);
    }

    @DeleteMapping("orders/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderDao.deleteById(id);
    }

    @GetMapping("version")
    public String getVersion() {
        return "Version 1.0";
    }

    @GetMapping("/api/test")
    public String test() {
        return "Access Granted!";
    }
}
