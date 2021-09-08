package com.lilierygina.detailingstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {
   private OrderService orderService;

  // constructor injection
  public OrderController(OrderService theOrderService) {
    orderService = theOrderService;
  }

  // add mapping for "/list"
  @GetMapping("/list")
  public String listOrders(Model theModel) {
    //get orders from db
    List<Order> theOrders = orderService.findAll();

    // add to the spring model (our Thymeleaf template will access this data
    theModel.addAttribute("orders", theOrders);
    return "orders/all-order-list";
  }

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model theModel) {
    // create model attribute to bind form data
    Order theOrder = new Order();

    // thymeleaf template will access this data for binding form data
    theModel.addAttribute("order", theOrder);

    return "orders/order-form";
  }

  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("orderId") int theId, Model theModel) {
    // get the order from the service
    Order theOrder = orderService.findById(theId);

    // set order as a model attribute to pre-populate the form
    theModel.addAttribute("order", theOrder);
    // send over to form
    return "orders/order-form";
  }

  @PostMapping("/save")
  public String saveOrder(@ModelAttribute("order") Order theOrder) {
    // save the order
    orderService.save(theOrder);
    // use a redirect to prevent duplicate submissions
    return "redirect:/orders/list";
  }

  @GetMapping("/delete")
  public String deleteOrder(@RequestParam("orderId") int theId) {
    // delete the order
    orderService.deleteById(theId);

    // redirect to /orders/list
    return "redirect:/orders/list";
  }
}
