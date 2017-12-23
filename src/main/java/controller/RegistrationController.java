package controller;

import controller.dao.UserDAO;
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


@Controller
// it puts into session an User object automatically
// like a Map with the key registeredUser and the value : user
@SessionAttributes(types = User.class)
public class RegistrationController {

    @Autowired
    UserDAO userDao;
    @Autowired
    HttpSession session;
    @Autowired
    FormValidator formValidator;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("registeredUser", user);
        return "registration";
    }

    @RequestMapping(value = "/userregistration", method = RequestMethod.POST)
    public ModelAndView doRegistration(
            //@ModelAttribute : it is the same modelAttribute as in jsp (registration.jsp)
            // data from the form should be moved to User u directly
            // so names of inputs should be the same as fields in User class
            @ModelAttribute("registeredUser")
            // to be validated by Validator
            @Validated
                    User u
            // results of form validation
            , BindingResult result) {
        ModelAndView mod = new ModelAndView();
        if (result.hasErrors()) {
            mod.setViewName("registration");
        } else {
            mod.setViewName("confirmation");
        }
        return mod;
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String getConfirmation(SessionStatus status) {
        userDao.create((User) session.getAttribute("registeredUser"));
        status.setComplete(); //обнуление сессии (delete and recreate new)
        // we have to clear data after registrations and provide new one to login
        return "login";
    }

    @InitBinder
    protected void initValidator(WebDataBinder binder) {
        // bind validator to controller
        binder.setValidator(this.formValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(SessionStatus status) {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(SessionStatus status) {
        status.setComplete(); //обнуление сессии (delete and recreate new)
        return "logout";
    }
}
