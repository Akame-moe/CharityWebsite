package com.charityconnector.controller;

import com.charityconnector.entity.Transaction;
import com.charityconnector.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@Controller
public class PaypalController {

    @Resource
    PaypalService paypalService;

    @RequestMapping(path = "/paypal", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Transaction> addPaypal(@RequestBody Transaction transaction) {
        Transaction res = paypalService.addPaypal(transaction);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(path = "/paypal", method = RequestMethod.PATCH)
    @ResponseBody
    public void updatePaypal(@RequestBody Transaction transaction) {
        paypalService.updateSelective(transaction);
    }

    @RequestMapping(path = "/paypal/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deletePaypalById(@PathVariable("id") Long id) {
        paypalService.deleteById(id);
    }

    @RequestMapping(path = "/paypal/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Transaction> getPaypalById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(paypalService.findById(id), HttpStatus.OK);
    }

}
