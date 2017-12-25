package controller;

import dao.OrderDAO;
import dao.UserDAO;
import entity.Order;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;


@Controller
@SessionAttributes(types = User.class)
public class OrderController {

    @Autowired
    OrderDAO orderDao;
    @Autowired
    HttpSession session;
    @Autowired
    FormValidatorOrder formValidator;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "order";
    }

    @RequestMapping(value = "/makeorder", method = RequestMethod.POST)
    public ModelAndView doRegistration(
            //@ModelAttribute : it is the same modelAttribute as in jsp (registration.jsp)
            // data from the form should be moved to User u directly
            // so names of inputs should be the same as fields in User class
            @ModelAttribute("order")
            // to be validated by Validator
            @Validated
                    Order order
            // results of form validation
            , BindingResult result) {
        ModelAndView mod = new ModelAndView();
        if (result.hasErrors()) {
            mod.setViewName("order");
        } else {
            mod.setViewName("confirmationorder");
        }
        return mod;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView doOrder(
            //@ModelAttribute : it is the same modelAttribute as in jsp (registration.jsp)
            // data from the form should be moved to User u directly
            // so names of inputs should be the same as fields in User class
            @ModelAttribute("order")
            // to be validated by Validator
            @Validated
                    Order order
            // results of form validation
            , BindingResult result) {
        ModelAndView mod = new ModelAndView();
        if (result.hasErrors()) {
            mod.setViewName("order");
        } else {
            mod.setViewName("confirmation");
        }
        return mod;
    }

    @RequestMapping(value = "/confirmationorder", method = RequestMethod.GET)
    public String getConfirmation(SessionStatus status) {
        Order order = (Order) session.getAttribute("order");
        order.setUser((User) session.getAttribute("user"));
        orderDao.create(order);
        status.setComplete(); //обнуление сессии (delete and recreate new)
        return "menu";
    }

    @RequestMapping(value = "/allorders", method = RequestMethod.POST)
    public ModelAndView getllOrdersForm() {

        ModelAndView mod = new ModelAndView();
        mod.addObject("orders", orderDao.getOrdersPeriod(LocalDate.of(1990, 1, 1), LocalDate.now()));
        mod.setViewName("allorders");
        return mod;
    }

}
