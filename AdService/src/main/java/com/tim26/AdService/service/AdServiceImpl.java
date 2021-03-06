package com.tim26.AdService.service;

import com.tim26.AdService.dto.*;
import com.tim26.AdService.model.*;
import com.tim26.AdService.model.Date;
import com.tim26.AdService.repository.AdRepository;
import com.tim26.AdService.repository.PricelistRepository;
import com.tim26.AdService.repository.UserRepository;
import com.tim26.AdService.service.interfaces.AdService;
import com.tim26.AdService.service.interfaces.CarService;
import com.tim26.AdService.service.interfaces.CodebookService;
import com.tim26.AdService.service.interfaces.PricelistService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(value = "adService")
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private CodebookService codebookService;

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private PricelistRepository pricelistRepository;

    private static int DEFAULT_PAGE_SIZE = 8;

    @Override
    public boolean save(CreateAdDto ad) throws SQLException {
        /*if(!validateCreationData(ad))
            return false;*/

        Ad advertisment = new Ad();
        Car car = new Car();
        User user = new User();
        user.setUsername(ad.getUsername());

        if(userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user.getUsername());
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
            PriceList priceList = pricelistService.findByName(ad.getPricelist());
            advertisment.setPriceList(priceList);
            //advertisment.setRentDates(ad.getDates());

            //looping trough each date range and setting its start & end date and list of dates inbetween
            //setting list of dates between start and end

            for(DateRange dt : ad.getDates()){


                LocalDate start = dt.getStartDate();
                LocalDate end = dt.getEndDate();
                List<Date> totalDates = new ArrayList<>();
                while (!start.isAfter(end)) {

                    totalDates.add(new Date(start, dt));
                    start = start.plusDays(1);
                }

                DateRange helper = new DateRange(dt.getStartDate(),dt.getEndDate());
                advertisment.getRentDates().add(helper);

            }

            user.getAd().add(advertisment);

            advertisment.setUser(user);

            try {
                adRepository.save(advertisment);
                return  true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean save(Ad ad) {
        if(adRepository.save(ad) != null)
            return true;
        else
            return false;
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
    public Page<AdDTO> findAllPageable(int page) {

        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE, Sort.by("id"));
        List<Ad> allAds = adRepository.findAll();
        List<AdDTO> adDTOS = new ArrayList<>();

        for (Ad ad: allAds) {
            AdDTO adDTO = new AdDTO(ad);
            adDTOS.add(adDTO);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), adDTOS.size());


        return new PageImpl<>(adDTOS.subList(start, end), pageable, adDTOS.size());
    }

    @Override
    public FilterParametersDTO getFilterParamteres() {
        List<Ad> ads = adRepository.findAll();

        FilterParametersDTO filterParametersDTO = new FilterParametersDTO();

        for(Ad ad : ads){
            if(!filterParametersDTO.getCities().contains(ad.getCity()))
                filterParametersDTO.getCities().add(ad.getCity());

            if(!filterParametersDTO.getModels().contains(ad.getCar().getModel()))
                filterParametersDTO.getModels().add(ad.getCar().getModel());

            if(!filterParametersDTO.getBrands().contains(ad.getCar().getBrand()))
                filterParametersDTO.getBrands().add(ad.getCar().getBrand());

            if(!filterParametersDTO.getFuel().contains(ad.getCar().getFuel()))
                filterParametersDTO.getFuel().add(ad.getCar().getFuel());

            if(!filterParametersDTO.getTransmission().contains(ad.getCar().getTransmission()))
                filterParametersDTO.getTransmission().add(ad.getCar().getTransmission());

            if(!filterParametersDTO.getCarClass().contains(ad.getCar().getCarClass()))
                filterParametersDTO.getCarClass().add(ad.getCar().getCarClass());

            if(!filterParametersDTO.getChildSeats().contains(ad.getCar().getChildSeats()))
                filterParametersDTO.getChildSeats().add(ad.getCar().getChildSeats());
        }

        double minPrice = ads.stream().map(ad -> ad.getPriceList().getDailyPrice()).min(Double::compare).get();
        double maxPrice =  ads.stream().map(ad -> ad.getPriceList().getDailyPrice()).max(Double::compare).get();
        double minKm =  ads.stream().map(ad -> ad.getCar().getKm()).min(Double::compare).get();
        double maxKm =  ads.stream().map(ad -> ad.getCar().getKm()).max(Double::compare).get();

        filterParametersDTO.setMinPrice(minPrice);
        filterParametersDTO.setMaxPrice(maxPrice);
        filterParametersDTO.setMinKm(minKm);
        filterParametersDTO.setMaxKm(maxKm);

        return filterParametersDTO;
    }

    @Override
    public boolean delete(AdDTO adDTO) {
        Ad ad = findAdById(adDTO.getId());
        if(!ad.getRentRequests().isEmpty())
            return false;

        adRepository.delete(ad);
        return true;
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
        if(createAdDto.getBrand().contains("<") || createAdDto.getBrand().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getModel().contains("<") || createAdDto.getModel().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getCarClass().contains("<") || createAdDto.getCarClass().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getFuel().contains("<") || createAdDto.getFuel().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getTransmission().contains("<") || createAdDto.getTransmission().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getCity().contains("<") || createAdDto.getCity().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.getPricelist().contains("<") || createAdDto.getPricelist().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        if(createAdDto.isCollision() != true && createAdDto.isCollision() != false) {
            LOGGER.error("Validate creation data for adding advertisment: FAILED, Boolean field is neither true nor false");
            return false;
        }

        if(!codebookService.getCarClasses().contains(createAdDto.getCarClass())) {
            LOGGER.error("Validation creation data for adding advertisment: FAILED, Provied {} car class doesn't exist.", createAdDto.getCarClass());
            return false;
        }

        if(!codebookService.getFuelTypes().contains(createAdDto.getFuel())) {
            LOGGER.error("Validation creation data for adding advertisment: FAILED, Provied {} fuel type doesn't exist.", createAdDto.getFuel());
            return false;
        }

        if(!codebookService.getTransmissionTypes().contains(createAdDto.getTransmission())) {
            LOGGER.error("Validation creation data for adding advertisment: FAILED, Provied {} transmission type doesn't exist.", createAdDto.getTransmission());
            return false;
        }

        if(!codebookService.getModelsFromBrand(createAdDto.getBrand()).contains(createAdDto.getModel())) {
            LOGGER.error("Validation creation data for adding advertisment: FAILED, Provied {} car model doesn't exist.", createAdDto.getModel());
            return false;
        }

        String km = String.valueOf(createAdDto.getKm());
        String kmLimit = String.valueOf(createAdDto.getKmLimit());
        String childSeats = String.valueOf(createAdDto.getChildSeats());
        
        if(createAdDto.getChildSeats().contains("-")) {
            LOGGER.error("Validation creation data for adding advertisment: FAILED, Number of child seats cannot be a negative number");
            return false;
        }

        LOGGER.info("Validation creation data for adding advertisment: SUCCESS");
        return true;
    }

    @Override
    public List<Ad> findByIds(List<Long> ids){
        return adRepository.findByIds(ids);
    }

    @Override
    public Page<AdDTO> filterAds(FilterDTO filterDTO) {

        ArrayList<AdDTO> returnads = new ArrayList<>();

        //creating an array of dates between start and end date
        LocalDate start = filterDTO.getStartDate();
        LocalDate end = filterDTO.getEndDate();
        List<LocalDate> dateArray = new ArrayList<>();
        while (!start.isAfter(end)) {
            dateArray.add(start);
            start = start.plusDays(1);
        }

       List<AdDTO> allAds = findAll();

        for(AdDTO adDTO : allAds){

            if(adDTO.getCity().equals(filterDTO.getCity())){


                for(DateRangeDTO period: adDTO.getRentDates()){

                    ArrayList<LocalDate> periodDateArray = new ArrayList<>();

                    LocalDate startAd = period.getStartDate();
                    LocalDate endAd = period.getEndDate();

                    while (!startAd.isAfter(endAd)) {
                        periodDateArray.add(startAd);
                        startAd = startAd.plusDays(1);
                    }

                    for (DateDTO date : period.getDates()){
                        periodDateArray.remove(date.getDate());
                    }

                    boolean isHere = true;
                    if(periodDateArray.contains(filterDTO.getStartDate()) == true) {

                        if (periodDateArray.contains(filterDTO.getEndDate()) == true) {

                                    for(LocalDate date : dateArray){
                                        if(periodDateArray.indexOf(date) == -1){
                                            isHere = false;
                                            break;
                                        }
                                    }

                                    if(isHere) {
                                        returnads.add(adDTO);
                                    }
                        }
                    }

                }

            }


        }

        List<Optional<Ad>> filteredAds = new ArrayList<>();
        List<AdDTO> filteredAdsDtos = new ArrayList<>();

        for(AdDTO adDTO : returnads){
            Optional<Ad> ad = adRepository.findById(adDTO.getId());
            filteredAds.add(ad);
        }

        for(Optional<Ad> ad: filteredAds) {
            if (filterDTO.getBrand() != "") {
                if (!filterDTO.getBrand().equals(ad.get().getCar().getBrand())) {
                    continue;
                }
            }
            if (filterDTO.getModel() != "") {
                if (!filterDTO.getModel().equals(ad.get().getCar().getModel())) {
                    continue;
                }
            }
            if (filterDTO.getFuel() != "") {
                if (!filterDTO.getFuel().equals(ad.get().getCar().getFuel())) {
                    continue;
                }
            }
            if (filterDTO.getTransmission() != "") {
                if (!filterDTO.getTransmission().equals(ad.get().getCar().getTransmission())) {
                    continue;
                }
            }
            if (filterDTO.getCarClass() != "") {
                if (!filterDTO.getCarClass().equals(ad.get().getCar().getCarClass())) {
                    continue;
                }
            }
            if (filterDTO.getChildSeats() != 0) {
                if (filterDTO.getChildSeats() != (ad.get().getCar().getChildSeats())) {
                    continue;
                }
            }
            if (ad.get().getPriceList().getDailyPrice() > filterDTO.getMaxPrice() ||
                    ad.get().getPriceList().getDailyPrice() < filterDTO.getMinPrice()) {
                continue;
            }
            if (ad.get().getCar().getKm() > filterDTO.getMaxKm() ||
                    ad.get().getCar().getKm() < filterDTO.getMinKm()) {
                continue;
            }
            if (filterDTO.getPlannedKm() > ad.get().getCar().getKmLimit()) {
                continue;
            }
            if (filterDTO.isCdw() && !ad.get().getCar().isCdw()) {
                continue;
            }

            AdDTO adDTO = new AdDTO(ad.get());
            filteredAdsDtos.add(adDTO);

        }

        if(filteredAdsDtos.size() == 0)
            return new PageImpl<>(filteredAdsDtos);

        if(filterDTO.getSort().equals("km"))
            filteredAdsDtos.sort(Comparator.comparingDouble(AdDTO::getKm));

        filteredAdsDtos.sort(Comparator.comparingDouble(AdDTO::getPrice));

        Pageable pageable = PageRequest.of(filterDTO.getPage(), DEFAULT_PAGE_SIZE);

        int startPage = (int) pageable.getOffset();
        int endPage = Math.min((startPage + pageable.getPageSize()), filteredAdsDtos.size());


        return new PageImpl<>(filteredAdsDtos.subList(startPage, endPage), pageable, filteredAdsDtos.size());
    }



    @Override
    public boolean setRentDatesForAds(List<RentAdDTO> rentAdDTOS) {
        List<Long> ids = rentAdDTOS.stream().map(RentAdDTO::getId).collect(Collectors.toList());
        List<Ad> ads = findByIds(ids);
        boolean save = false;
        if(ads == null)
            return false;

        for(Ad ad : ads){
            for(RentAdDTO rentAdDTO : rentAdDTOS){
                if(ad.getId().equals(rentAdDTO.getId())) {
                    for(DateRange dateRange : ad.getRentDates()) {
                        if(!(rentAdDTO.getStartDate().isAfter(dateRange.getEndDate()) || rentAdDTO.getEndDate().isBefore(dateRange.getStartDate()))) {
                            LocalDate tempStart = rentAdDTO.getStartDate();
                            while (!tempStart.isAfter(rentAdDTO.getEndDate())) {
                                dateRange.getDates().add(new Date(tempStart, dateRange));
                                tempStart = tempStart.plusDays(1);
                                save = true;
                            }
                        }
                    }
                }
            }

            if(save){
                save(ad);
                save = false;
            }
        }

        return true;
    }


}