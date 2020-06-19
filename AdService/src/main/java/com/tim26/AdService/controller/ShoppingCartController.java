package com.tim26.AdService.controller;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.User;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.time.LocalDate;
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
        public LocalDate startDate;
        public LocalDate endDate;
    }

    @GetMapping
    public List<AdDTO> getShoppingCart(Principal principal){
        return shoppingCartService.getCartData(principal);
    }

    @PostMapping
    public ResponseEntity<?> addToShoppingCart(@RequestBody AdCartDTO adCartDTO, Principal principal){
        if(shoppingCartService.addAd(principal.getName(), Long.parseLong(adCartDTO.adId), adCartDTO.startDate, adCartDTO.endDate))
            return ResponseEntity.ok().build();
        else
            return new ResponseEntity<>("You have already ordered this car!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFromShoppingCart(Principal principal, @PathVariable String id){
        if(shoppingCartService.removeAd(principal.getName(), Long.parseLong(id)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public Boolean deleteMultipleFromShoppingCart(@RequestParam List<Long> id, Principal principal){
        for(Long x : id){
            if(!shoppingCartService.removeAd(principal.getName(), x))
                return false;
        }
        return true;
    }

}
