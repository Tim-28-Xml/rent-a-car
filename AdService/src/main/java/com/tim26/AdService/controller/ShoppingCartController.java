package com.tim26.AdService.controller;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.model.Ad;
import com.tim26.AdService.model.User;
import com.tim26.AdService.service.interfaces.ShoppingCartService;
import com.tim26.AdService.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PreAuthorize("hasAnyAuthority('ORDER','USE_CART')")
public class ShoppingCartController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ShoppingCartController.class);

    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public static class AdCartDTO {
        public String adId;
        public LocalDate startDate;
        public LocalDate endDate;

        public String getAdId() {
            return adId;
        }

        public void setAdId(String adId) {
            this.adId = adId;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }

    @GetMapping
    public List<AdDTO> getShoppingCart(Principal principal){
        return shoppingCartService.getCartData(principal);
    }

    @PostMapping
    public ResponseEntity<?> addToShoppingCart(@RequestBody AdCartDTO adCartDTO, Principal principal){
        if(shoppingCartService.addAd(principal.getName(), Long.parseLong(adCartDTO.adId), adCartDTO.startDate, adCartDTO.endDate)) {
            LOGGER.info("Add advertisment with id: {} to the shopping cart with start date {} and end date {} ", adCartDTO.getAdId(), adCartDTO.getStartDate(), adCartDTO.getEndDate());
            return ResponseEntity.ok().build();
        }
        else {
            LOGGER.error("Failed to add Advertisment with id: {} to the Shopping Cart", adCartDTO.getAdId());
            return new ResponseEntity<>("You have already ordered this car!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFromShoppingCart(Principal principal, @PathVariable String id){
        if(shoppingCartService.removeAd(principal.getName(), Long.parseLong(id))) {
            LOGGER.info("Delete Advertisment with id: {} from the Shopping Cart", id);
            return ResponseEntity.noContent().build();
        }
        else {
            LOGGER.error("Failed to delete Advertisment with id: {} from the Shopping Cart", id);
            return ResponseEntity.badRequest().build();
        }
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
