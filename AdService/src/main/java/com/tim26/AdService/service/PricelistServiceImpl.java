package com.tim26.AdService.service;

import com.tim26.AdService.controller.PricelistController;
import com.tim26.AdService.dto.CreatePricelistDto;
import com.tim26.AdService.model.PriceList;
import com.tim26.AdService.model.User;
import com.tim26.AdService.repository.PricelistRepository;
import com.tim26.AdService.repository.UserRepository;
import com.tim26.AdService.service.interfaces.PricelistService;
import com.tim26.AdService.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistServiceImpl implements PricelistService {

    private static final Logger LOGGER= LoggerFactory.getLogger(PricelistServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean save(Principal p, CreatePricelistDto createPricelistDto) throws SQLException {
        if(!validateCreationData(createPricelistDto)) {
            return false;
        }

        Connection connection = null;
        String dbUrl = "jdbc:h2:file:./src/main/resources/adsDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false;AUTO_RECONNECT=TRUE;TRACE_LEVEL_FILE=0";
        String dbUsername = "sa";
        String dbPassword = "";
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementUser = null;

        try {

            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if(!userRepository.findByUsername(p.getName()).isPresent()) {
                preparedStatementUser = connection.prepareStatement("INSERT INTO user (username) values (?)");
                preparedStatementUser.executeUpdate();
            }
            User user = userService.findByUsername(p.getName());
            if(user != null) {
                PriceList priceList = new PriceList();
                priceList.setCdwPrice(createPricelistDto.getCdwPrice());
                priceList.setDailyPrice(createPricelistDto.getDailyPrice());
                priceList.setPricePerExtraKm(createPricelistDto.getPricePerExtraKm());
                priceList.setName(createPricelistDto.getName());
                priceList.setUser(user);
                user.getPriceLists().add(priceList);

                try {
                    int pricelists = pricelistRepository.findAll().size();
                    Long pId = Long.valueOf(++pricelists);
                    priceList.setId(pId);
                    preparedStatement = connection.prepareStatement("INSERT INTO price_list (id, cdw_price, daily_price,name, price_per_extra_km, user_username) values (?, ?,?,?,?,?)");
                    preparedStatement.setLong(1, priceList.getId());
                    preparedStatement.setDouble(2, priceList.getCdwPrice());
                    preparedStatement.setDouble(3, priceList.getDailyPrice());
                    preparedStatement.setString(4, priceList.getName());
                    preparedStatement.setDouble(5, priceList.getPricePerExtraKm());
                    preparedStatement.setString(6, user.getUsername());
                    preparedStatement.executeUpdate();
                } catch (Exception e) {
                    LOGGER.error("Failed to save pricelist - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} to the database \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
                    return  false;
                }
            } else {
                LOGGER.error("Failed to save pricelist - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} to the database \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
                return false;
            }
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(preparedStatementUser != null) {
                    preparedStatement.close();
                }

                if(connection != null) {
                    connection.close();
                    return  true;
                }

                return  true;
            } catch (Exception e) {
                LOGGER.error("Failed to save pricelist - Name: {}, \n Daily price: {}, \n Price with cdw: {}, \n Price if the km limit is passed: {} to the database \n", createPricelistDto.getName(), createPricelistDto.getDailyPrice(), createPricelistDto.getCdwPrice(), createPricelistDto.getPricePerExtraKm());
                return  false;
            }
        }
    }

    @Override
    public List<CreatePricelistDto> findAll() {
        List<PriceList> all = pricelistRepository.findAll();
        List<CreatePricelistDto> dtos = new ArrayList<>();

        for (PriceList p: all) {
            CreatePricelistDto dto = modelMapper.map(p,CreatePricelistDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public PriceList findByName(String name) {
        return pricelistRepository.findByName(name);
    }

    @Override
    public boolean validateCreationData(CreatePricelistDto dto) {
        if(dto.getCdwPrice() < 0 || dto.getDailyPrice() < 0 || dto.getPricePerExtraKm() < 0) {
            LOGGER.error("Validation creation data for adding pricelist: FAILED, Any of the prices fields cannot be negative");
            return false;
        }

        if(dto.getName().isEmpty()) {
            LOGGER.error("Validation creation data for adding pricelist: FAILED, Name of the pricelist cannot be empty");
            return false;
        }

        if(dto.getName().contains("<") || dto.getName().contains(">")) {
            LOGGER.error("Prevented XSS Attack");
            return false;
        }

        LOGGER.info("Validation creation data for adding pricelist: SUCCESS");

        return true;
    }

    @Override
    public boolean delete(CreatePricelistDto pricelistDto) {

        PriceList pricelist = findByName(pricelistDto.getName());

        if(!pricelist.getAds().isEmpty())
            return false;

        pricelistRepository.delete(pricelist);
        return true;
    }

    @Override
    public PriceList findById(Long id) {
        return pricelistRepository.findById(id);
    }


}
