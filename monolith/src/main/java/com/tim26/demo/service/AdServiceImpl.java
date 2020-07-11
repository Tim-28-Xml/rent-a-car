package com.tim26.demo.service;

import com.tim26.demo.dto.*;
import com.tim26.demo.model.*;
import com.tim26.demo.model.Date;
import com.tim26.demo.repository.AdRepository;
import com.tim26.demo.service.interfaces.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private UService userService;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${exchange}")
    String exchange;

    @Value("${ad-service-key}")
    private String adKey;

    @Override
    public boolean save(CreateAdDto ad) {
        Agent agent = (Agent) userService.findByUsername(ad.getUsername());
        User user = userService.findByUsername(ad.getUsername());

        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(r -> r.contains("ROLE"))
                .findFirst().get();

        ad.setRole(role);

        if(agent != null) {

            Ad advertisment = new Ad();
            Car car = new Car();

            if(ad != null) {
                car.setBrand(ad.getBrand());
                car.setCarClass(ad.getCarClass());
                car.setModel(ad.getModel());
                car.setTransmission(ad.getTransmission());
                car.setFuel(ad.getFuel());
                car.setKm(ad.getKm());
                car.setKmLimit(ad.getKmLimit());
                car.setChildSeats(Integer.parseInt(ad.getChildSeats()));
                car.setCdw(ad.isCollision());
                List<byte[]> imgBytes = new ArrayList<byte[]>();

                for(String img : ad.getFiles()) {
                    byte[] imgByte = Base64.decodeBase64(img.getBytes());
                    imgBytes.add(imgByte);
                }
                car.setFiles(imgBytes);
                advertisment.setCar(car);
                advertisment.setCity(ad.getCity());



                for(DateRange dt : ad.getDates()){

                    LocalDate start = dt.getStartDate();
                    LocalDate end = dt.getEndDate();
                    List<Date> totalDates = new ArrayList<>();
                    while (!start.isAfter(end)) {

                        totalDates.add(new Date(start));
                        start = start.plusDays(1);
                    }

                    DateRange helper = new DateRange(dt.getStartDate(),dt.getEndDate(),totalDates);
                    advertisment.getRentDates().add(helper);

                }


                agent.getAd().add(advertisment);
                advertisment.setUser(agent);

                PriceList priceList = pricelistService.findByName(ad.getPricelist());
                advertisment.setPriceList(priceList);

                try {
                    adRepository.save(advertisment);
                    rabbitTemplate.convertAndSend(exchange, adKey, ad);
                    return  true;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<AdDTO> findAll() {
        List<Ad> allAds = adRepository.findAll();
        List<AdDTO> adDTOS = new ArrayList<>();

        for (Ad ad: allAds) {
            AdDTO adDTO = new AdDTO(ad);
            adDTOS.add(adDTO);
        }
        return adDTOS;
    }

    @Override
    public AdDTO findById(long id) {
        Ad ad = adRepository.findById(id);

        if(ad != null){
            AdDTO adDTO = new AdDTO(ad);
            return adDTO;
        }

        return null;
    }

    @Override
    public List<AdDTO> findMyAds(String username) {
        List<AdDTO> adDTOS = new ArrayList<>();

        User user = userService.findByUsername(username);
        List<Ad> ads = user.getAd();

        for (Ad a : ads) {
            adDTOS.add(new AdDTO(a));
        }

        return adDTOS;
    }

    @Override
    public boolean rentByCreator(RentAdDTO rentAdDTO, Principal p) {
        Optional<Ad> ad = adRepository.findById(rentAdDTO.getId());
        if (!ad.isPresent()) {
            return false;
        }

        LocalDate startDate = rentAdDTO.getStartDate();
        LocalDate endDate = rentAdDTO.getEndDate();

        List<RentRequest> goodRequests = new ArrayList<>();

        for (RentRequest rentRequest : ad.get().getRentRequests()) {
            for(AdDateRange dr : rentRequest.getAdsWithDates()) {

                if (dr.getStart().isAfter(startDate) && dr.getStart().isBefore(endDate)) {
                    break;
                } else if (dr.getEnd_Date().isAfter(startDate) && dr.getEnd_Date().isBefore(endDate)) {
                    break;
                } else {
                    goodRequests.add(rentRequest);
                }
            }
        }

        ad.get().getRentRequests().clear();
        ad.get().setRentRequests(goodRequests);

        DateRange dateRange = new DateRange(startDate, endDate);
        ad.get().getRentDates().add(dateRange);
        adRepository.save(ad.get());

        return true;

    }

    @Override
    public List<AdDTO> findHighestMileage(Principal p) {


        User user = userService.findByUsername(p.getName());
        List<Ad> ads =  user.getAd();

        List<AdDTO> dtos = new ArrayList<>();

        Collections.sort(ads, new Comparator<Ad>(){

            public int compare(Ad ad1, Ad ad2) {
                int value = (int) Math.round(ad2.getCar().getKm() - ad1.getCar().getKm());
                return value;
            }
        });


        for(Ad ad : ads){
            AdDTO adDTO = new AdDTO(ad);
            dtos.add(adDTO);
        }

    return dtos;
    }

    @Override
    public List<AdDTO> findHighestRating(Principal p) {

        User user = userService.findByUsername(p.getName());
        List<Ad> ads =  user.getAd();
        List<AdDTO> dtos = new ArrayList<>();
        AdDTO dto = null;

        double sum = 0;

        for(Ad ad : ads){

            if(ad.getReviews().size() != 0) {

                for(Review review : ad.getReviews()){

                sum = sum + review.getRating();
            }

             dto = new AdDTO(new CarDTO(ad.getCar()),p.getName(),sum/(ad.getReviews().size()));

            }else{

                dto = new AdDTO(new CarDTO(ad.getCar()),p.getName(),0);

            }

            dtos.add(dto);

        }


        Collections.sort(dtos, new Comparator<AdDTO>(){

            public int compare(AdDTO ad1, AdDTO ad2) {
                int value = (int) Math.round(ad2.getRating() - ad1.getRating());
                return value;
            }
        });


        return dtos;


    }

    @Override
    public List<AdDTO> findMostReviews(Principal p) {

        User user = userService.findByUsername(p.getName());
        List<Ad> ads =  user.getAd();

        List<AdDTO> dtos = new ArrayList<>();

        Collections.sort(ads, new Comparator<Ad>(){

            public int compare(Ad ad1, Ad ad2) {
                int value = (int) Math.round(ad2.getReviews().size() - ad1.getReviews().size());
                return value;
            }
        });


        for(Ad ad : ads){
            AdDTO adDTO = new AdDTO(new CarDTO(ad.getCar()),p.getName(),ad.getReviews().size());
            dtos.add(adDTO);
        }

        return dtos;
    }

    @Override
    public boolean isMyAd(Principal p,Long id) {
        List<Ad> ads = userService.findByUsername(p.getName()).getAd();
        boolean is = false;

        for(Ad ad : ads){
            if (ad.getId() == id){
                is=true;
            }
        }

        return  is;
    }


}
