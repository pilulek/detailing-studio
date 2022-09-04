package com.lilierygina.detailingstudio.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.lilierygina.detailingstudio.entity.Order;
import com.lilierygina.detailingstudio.entity.ServicesList;
import com.lilierygina.detailingstudio.service.DocumentGenerator;
import com.lilierygina.detailingstudio.service.OrderService;
import com.lilierygina.detailingstudio.service.ServicesListService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
        private final OrderService orderService;
        private final ServicesListService servicesListService;
        private final DocumentGenerator documentGenerator;

        public OrderController(OrderService theOrderService,
                               ServicesListService servicesListService,
                               DocumentGenerator documentGenerator) {
                this.orderService = theOrderService;
                this.servicesListService = servicesListService;
                this.documentGenerator = documentGenerator;
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
        @GetMapping("/showDocument")
        public ResponseEntity<InputStreamResource> showPDFDocument(@RequestParam("orderId") int theId, Model theModel) throws DocumentException, IOException, URISyntaxException {
                Order theOrder = orderService.findById(theId);
                ServicesList theServicesList = servicesListService.findByOrderId(theId);
                theModel.addAttribute("order", theOrder);
                theModel.addAttribute("servicesList", theServicesList);

                Document document = documentGenerator.generateDocument(theModel, theOrder, theServicesList);

                String filePath = "src/main/pdfdocuments/iTextTable.pdf";
                File file = new File(filePath);
                HttpHeaders headers = new HttpHeaders();
                headers.add("content-disposition", "inline;filename=" +filePath);

                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);
        }

        @GetMapping("/showFormForAdd")
        public String showFormForAdd(Model theModel) {
                // create model attribute to bind form data
                Order theOrder = new Order();
                ServicesList theServicesList = new ServicesList();
                theOrder.setServicesList(theServicesList);

                // thymeleaf template will access this data for binding form data
                theModel.addAttribute("order", theOrder);
                theModel.addAttribute("servicesList", theServicesList);

                return "orders/order-form";
        }

        @GetMapping("/showFormForUpdate")
        public String showFormForUpdate(@RequestParam("orderId") int theId, Model theModel) {
                // get the order from the service
                Order theOrder = orderService.findById(theId);
                ServicesList theServicesList = servicesListService.findByOrderId(theId);

                // set order as a model attribute to pre-populate the form
                theModel.addAttribute("order", theOrder);
                theModel.addAttribute("servicesList", theServicesList);
                // send over to form
                return "orders/order-form";
        }

        @PostMapping("/save")
        public String saveOrder(@ModelAttribute("order") Order theOrder, ServicesList servicesList) {
                // save the order
                theOrder.setServicesList(servicesList);
                servicesListService.save(new ServicesList(servicesList));
                orderService.save(theOrder);

                // use a redirect to prevent duplicate submissions
                return "redirect:/orders/list";
        }

        @GetMapping("/update")
        public String updateOrder(@ModelAttribute("order") Order theOrder) {
                // save the order
                orderService.save(theOrder);
                // use a redirect to prevent duplicate submissions
                return "orders/order-form";
        }

        @GetMapping("/delete")
        public String deleteOrder(@RequestParam("orderId") int theId) {
                // delete the order
                orderService.deleteById(theId);

                // redirect to /orders/list
                return "redirect:/orders/list";
        }
}
