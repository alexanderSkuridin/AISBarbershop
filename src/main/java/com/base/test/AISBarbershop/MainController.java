package com.base.test.springonetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("masters", barberRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        return "index";
    }

    @GetMapping("/master")
    public String master(Model model) {
        model.addAttribute("masters", barberRepository.findAll());
        return "barber";
    }

    @PostMapping(path="/addMaster")
    public String addMaster (@RequestParam String name, @RequestParam String phone) {
        try {
            Barber n = new Barber();
            n.setName(name);
            n.setPhone(phone);
            barberRepository.save(n);
            return "redirect:/master";
        } catch (Exception ignored) {}
        return "redirect:/master";
    }

    @PostMapping(path="/removeMaster")
    public String removeMaster (@RequestParam long id) {
        try {
            barberRepository.deleteById(id);
            return "redirect:/master";
        } catch (Exception ignored) {}
        return "redirect:/master";
    }

    @PostMapping(path="/updateMaster")
    public String updateMaster (@RequestParam String uname,
                                @RequestParam String uphone,
                                @RequestParam Long uid) {
        try {
            Barber barberToUpdate = barberRepository.getOne(uid);
            barberToUpdate.setName(uname);
            barberToUpdate.setPhone(uphone);
            barberRepository.save(barberToUpdate);
            return "redirect:/master";
        } catch (Exception ignored) {}
        return "redirect:/master";
    }

    @GetMapping("/customer")
    public String customer(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer";
    }

    @PostMapping(path="/addCustomer")
    public String addCustomer (@RequestParam String name, @RequestParam String phone, @RequestParam String note) {
        try {
            Customer n = new Customer();
            n.setName(name);
            n.setPhone(phone);
            n.setNote(note);
            customerRepository.save(n);
            return "redirect:/customer";
        } catch (Exception ignored) {}
        return "redirect:/customer";
    }

    @PostMapping(path="/removeCustomer")
    public String removeCustomer (@RequestParam long id) {
        try {
            customerRepository.deleteById(id);
            return "redirect:/customer";
        } catch (Exception ignored) {}
        return "redirect:/customer";
    }

    @PostMapping(path="/updateCustomer")
    public String updateCustomer (@RequestParam String uname,
                                @RequestParam String uphone,
                                @RequestParam String unote,
                                @RequestParam Long uid) {
        try {
            Customer customerToUpdate = customerRepository.getOne(uid);
            customerToUpdate.setName(uname);
            customerToUpdate.setPhone(uphone);
            customerToUpdate.setNote(unote);
            customerRepository.save(customerToUpdate);
            return "redirect:/customer";
        } catch (Exception ignored) {}
        return "redirect:/customer";
    }

    @GetMapping("/service")
    public String service(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "service";
    }

    @PostMapping(path="/addService")
    public String addService (@RequestParam String name, @RequestParam String desc, @RequestParam BigDecimal price) {
        try {
            Service n = new Service();
            n.setName(name);
            n.setDescription(desc);
            n.setAmount(price);
            serviceRepository.save(n);
            return "redirect:/service";
        } catch (Exception ignored) {}
        return "redirect:/service";
    }

    @PostMapping(path="/removeService")
    public String removeService (@RequestParam long id) {
        try {
            serviceRepository.deleteById(id);
            return "redirect:/service";
        } catch (Exception ignored) {}
        return "redirect:/service";
    }

    @PostMapping(path="/updateService")
    public String updateService (@RequestParam String uname,
                                  @RequestParam String udesc,
                                  @RequestParam BigDecimal uprice,
                                  @RequestParam Long uid) {
        try {
            Service serviceToUpdate = serviceRepository.getOne(uid);
            serviceToUpdate.setName(uname);
            serviceToUpdate.setDescription(udesc);
            serviceToUpdate.setAmount(uprice);
            serviceRepository.save(serviceToUpdate);
            return "redirect:/service";
        } catch (Exception ignored) {}
        return "redirect:/service";
    }

    @GetMapping("/order")
    public String order(Model model) {
        model.addAttribute("masters", barberRepository.findAll());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        return "order";
    }

    @PostMapping(path="/addOrder")
    public String addOrder (@RequestParam Long master,
                            @RequestParam Long customer,
                            @RequestParam Long service,
                            @RequestParam String date,
                            @RequestParam String time) {
        try {
            Order n = new Order();
            String str = date + " " + time;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            n.setServiceAt(dateTime);
            n = orderRepository.save(n);
            Barber barb = barberRepository.getOne(master);
            Customer cust = customerRepository.getOne(customer);
            Service serv  = serviceRepository.getOne(service);
            n.barber = barb;
            n.customer = cust;
            n.service = serv;
            barb.getOrders().add(n);
            barberRepository.save(barb);
            cust.getOrders().add(n);
            customerRepository.save(cust);
            serv.getOrders().add(n);
            serviceRepository.save(serv);
            orderRepository.save(n);
            return "redirect:/order";
        } catch (Exception ignored) {}
        return "redirect:/order";
    }

    @PostMapping(path="/removeOrder")
    public String removeOrder (@RequestParam long id) {
        try {
            orderRepository.deleteById(id);
            return "redirect:/order";
        } catch (Exception ignored) {}
        return "redirect:/order";
    }
}
