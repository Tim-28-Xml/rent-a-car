package com.tim26.demo.controller;

import com.tim26.demo.model.Ad;
import com.tim26.demo.model.EndUser;
import com.tim26.demo.service.interfaces.EndUserService;
import com.tim26.demo.service.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    EndUserService endUserService;

    @Autowired
    ShoppingCartService shoppingCartService;


    @GetMapping
    public List<Ad> getShoppingCart(Principal principal){
        EndUser endUser = endUserService.findByUsername(principal.getName());
        return endUser.getShoppingCart();
    }

    @PostMapping
    public ResponseEntity addToShoppingCart(@RequestBody Ad ad, Principal principal){
        shoppingCartService.addAd(principal.getName(), ad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity deleteFromShoppingCart(@PathVariable String id, Principal principal){

        if(shoppingCartService.removeAd(principal.getName(), id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }


}
