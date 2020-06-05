package com.tim26.AdService.controller;

import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.User;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public static class UserAd {
        public String userId;
        public String adId;
    }

    @GetMapping
    @RequestMapping("{userId}")
    public List<Ad> getShoppingCart(@PathVariable String userId){
        User user = userService.findById(Long.parseLong(userId));
        return user.getShoppingCart();
    }

    @PostMapping
    public ResponseEntity addToShoppingCart(@RequestBody UserAd userAd){
        shoppingCartService.addAd(Long.parseLong(userAd.userId), Long.parseLong(userAd.adId));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteFromShoppingCart(@RequestBody UserAd userAd){
        if(shoppingCartService.removeAd(Long.parseLong(userAd.userId), Long.parseLong(userAd.adId)))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
