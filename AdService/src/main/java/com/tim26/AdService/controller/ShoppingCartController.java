package com.tim26.AdService.controller;

import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.User;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
@PreAuthorize("hasAuthority('ORDER')")
public class ShoppingCartController {

    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public static class AdCartDTO {
        public String adId;
    }

    @GetMapping
    public List<Ad> getShoppingCart(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return user.getShoppingCart();
    }

    @PostMapping
    public ResponseEntity addToShoppingCart(@RequestBody AdCartDTO adCartDTO, Principal principal){
        if(shoppingCartService.addAd(principal.getName(), Long.parseLong(adCartDTO.adId)))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity deleteFromShoppingCart(@RequestBody AdCartDTO adCartDTO, Principal principal){
        if(shoppingCartService.removeAd(principal.getName(), Long.parseLong(adCartDTO.adId)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

}