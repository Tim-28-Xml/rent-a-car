package com.tim26.AdService.service;

import com.tim26.AdService.dto.AdDTO;
import com.tim26.AdService.dto.CarDTO;
import com.tim26.AdService.dto.CreateAdDto;
import com.tim26.AdService.dto.RentAdDTO;
import com.tim26.AdService.model.*;
import com.tim26.AdService.repository.AdRepository;
import com.tim26.AdService.repository.UserRepository;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.CarService;
import com.tim26.AdService.service.interfaces.CodebookService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;

@Service
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER=LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private CodebookService codebookService;

    @Override
    public boolean save(CreateAdDto ad, Principal p) throws SQLException {
        if(!validateCreationData(ad))
            return false;

        Ad advertisment = new Ad();
        Car car = new Car();
        User user = new User();
        user.setUsername(ad.getCurrentUser());

        if(!userRepository.findByUsername(ad.getCurrentUser()).isPresent()) {
            userRepository.save(ad.getCurrentUser());
        }

        if(ad.getRole().equals("ROLE_USER")) {
            if(user.getAd() != null) {
                if(user.getAd().size() == 3) {
                    return false;
                }
            }
        }

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
            //advertisment.setRentDates(ad.getDates());

            //looping trough each date range and setting its start & end date and list of dates inbetween
            //setting list of dates between start and end

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

            user.getAd().add(advertisment);

            advertisment.setUser(user);

            try {
                advertisment = adRepository.save(advertisment);
                return  true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

        /*Connection connection = null;
        String dbUrl = "jdbc:h2:file:./src/main/resources/adsDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;AUTO_RECONNECT=TRUE;TRACE_LEVEL_FILE=0";
        String dbUsername = "sa";
        String dbPassword = "";

        PreparedStatement preparedStatementAd = null;
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementCar = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            Ad advertisment = new Ad();
            Car car = new Car();
            User user = new User();
            user.setUsername(p.getName());

            if(!userRepository.findByUsername(ad.getCurrentUser()).isPresent()) {
                preparedStatementUser = connection.prepareStatement("INSERT INTO user (username) values (?)");
                preparedStatementUser.executeUpdate();
            }

            if(ad.getRole().equals("ROLE_USER")) {
                if(user.getAd() != null) {
                    if(user.getAd().size() == 3) {
                        return false;
                    }
                }
            }

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

                //looping trough each date range and setting its start & end date and list of dates inbetween
                //setting list of dates between start and end
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

                user.getAd().add(advertisment);
                advertisment.setUser(user);

                try {
                    preparedStatementCar = connection.prepareStatement("INSERT INTO car (brand, car_class, model, fuel, transmission, km, km_limit, child_seats, cdw) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    preparedStatementCar.setString(1, car.getBrand());
                    preparedStatementCar.setString(2, car.getCarClass());
                    preparedStatementCar.setString(3, car.getModel());
                    preparedStatementCar.setString(4, car.getFuel());
                    preparedStatementCar.setString(5, car.getTransmission());
                    preparedStatementCar.setDouble(6, car.getKm());
                    preparedStatementCar.setDouble(7, car.getKmLimit());
                    preparedStatementCar.setInt(8, car.getChildSeats());
                    preparedStatementCar.setBoolean(9, car.isCdw());
                    preparedStatementCar.executeUpdate();

                    preparedStatementAd = connection.prepareStatement("INSERT INTO ad (car_id, city, user_id) values (?, ?, ?)");
                    preparedStatementAd.setLong(1, car.getId());
                    preparedStatementAd.setString(2, advertisment.getCity());
                    preparedStatementAd.setString(3, user.getUsername());
                    preparedStatementAd.executeUpdate();
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } finally {
            try {
                if (preparedStatementUser != null || preparedStatementAd != null || preparedStatementCar != null) {
                    preparedStatementUser.close();
                    preparedStatementAd.close();
                    preparedStatementCar.close();
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
            try {
                if (connection != null) {
                    connection.close();
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }*/
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

    public CarDTO findCarById(Long id){
        return carService.findById(id);
    }

    @Override
    public List<AdDTO> findMyAds(String username) {

        List<AdDTO> adDTOS = new ArrayList<>();

        if(!userRepository.findByUsername(username).isPresent()) {
            return adDTOS;
        }

        Optional<User> user = userRepository.findByUsername(username);
        List<Ad> ads = user.get().getAd();

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
            if (rentRequest.getReqStartDate().isAfter(startDate) && rentRequest.getReqStartDate().isBefore(endDate)) {
                break;
            } else if (rentRequest.getReqEndDate().isAfter(startDate) && rentRequest.getReqEndDate().isBefore(endDate)) {
                break;
            } else {
                goodRequests.add(rentRequest);
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
    public Ad findAdById(Long id){
        return adRepository.findById(id).orElse(null);
    }

    @Override
    public boolean validateCreationData(CreateAdDto createAdDto) {
        if(createAdDto.isCollision() != true && createAdDto.isCollision() != false)
            return false;

        if(!codebookService.getCarClasses().contains(createAdDto.getCarClass()))
            return false;

        if(!codebookService.getFuelTypes().contains(createAdDto.getFuel()))
            return false;

        if(!codebookService.getTransmissionTypes().contains(createAdDto.getTransmission()))
            return false;

        if(!codebookService.getModelsFromBrand(createAdDto.getBrand()).contains(createAdDto.getModel()))
            return false;

        for(BrandModels b : codebookService.getBrands()) {
            if(!b.getBrand().equals(createAdDto)) {
                return false;
            }
        }

        if(!userRepository.findByUsername(createAdDto.getCurrentUser()).isPresent())
            return false;

        String km = String.valueOf(createAdDto.getKm());
        String kmLimit = String.valueOf(createAdDto.getKmLimit());
        String childSeats = String.valueOf(createAdDto.getChildSeats());

        return true;
    }

    @Override
    public List<Ad> findByIds(List<Long> ids){
        return adRepository.findByIds(ids);
    }
}