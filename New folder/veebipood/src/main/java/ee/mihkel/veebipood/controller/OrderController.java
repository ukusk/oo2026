package ee.mihkel.veebipood.controller;

import ee.mihkel.veebipood.dto.OrderRowDto;
import ee.mihkel.veebipood.dto.ParcelMachine;
import ee.mihkel.veebipood.dto.PaymentUrl;
import ee.mihkel.veebipood.entity.Order;
import ee.mihkel.veebipood.entity.OrderRow;
import ee.mihkel.veebipood.repository.OrderRepository;
import ee.mihkel.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("parcelmachines")
    public List<ParcelMachine> getParcelMachines(@RequestParam String country) {
        String url = "https://www.omniva.ee/locations.json";
        ParcelMachine[] response = restTemplate.exchange(url, HttpMethod.GET, null, ParcelMachine[].class).getBody();
        return Arrays.stream(response)
                .filter(e -> e.getA0_name().equals(country.toUpperCase()))
                .toList();
    }


    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id); // kustutan
        return orderRepository.findAll(); // uuenenud seis
    }

    // person --> autentimise tokenist. parcelmachine --> Omnivast
    // localhost:8080/orders?personId=1
    @PostMapping("orders")
    public PaymentUrl addOrder(@RequestParam Long personId,
                                @RequestParam(required = false) String parcelMachine,
                                @RequestBody List<OrderRowDto> orderRows){
        Order order = orderService.saveOrder(personId, parcelMachine, orderRows); // siin salvestab
        return orderService.makePayment(order.getId(), order.getTotal());
        //return orderRepository.findAll(); // siin on uuenenud seis
    }

    //@PostMapping("pay")
    //public PaymentUrl makePayment(@RequestParam Long orderId, double sum) {
    //    return orderService.makePayment(orderId, sum);
    //}
}
