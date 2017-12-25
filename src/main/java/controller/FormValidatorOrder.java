package controller;


import entity.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FormValidatorOrder implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        // this validator can be applied only to Order class
        return Order.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "region", "error.enterRegion");
        ValidationUtils.rejectIfEmpty(errors, "dateOrder", "error.enterDateOrder");
        ValidationUtils.rejectIfEmpty(errors, "sumOrder", "error.enterSumOrder");

    }

}
