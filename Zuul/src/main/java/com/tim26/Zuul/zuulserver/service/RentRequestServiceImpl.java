package com.tim26.Zuul.zuulserver.service;

import com.tim26.Zuul.zuulserver.client.AdsClient;
import com.tim26.Zuul.zuulserver.client.RentRequestClient;
import com.tim26.Zuul.zuulserver.controller.AggregationController;
import com.tim26.Zuul.zuulserver.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentRequestServiceImpl implements RentRequestService{

    private RentRequestClient rentRequestClient;
    private AdsClient adsClient;

    public RentRequestServiceImpl(RentRequestClient rentRequestClient, AdsClient adsClient) {
        this.rentRequestClient = rentRequestClient;
        this.adsClient = adsClient;
    }

    @Override
    public ResponseEntity postRentRequest(ViewRequestDTO dto, String token){
        List<Long> ad_ids = dto.getAdsWithDates().stream().map(AdDateRange::getAd_id).collect(Collectors.toList());

        if(!adsClient.deleteMultipleFromShoppingCart(ad_ids, token))
            return ResponseEntity.badRequest().build();

        ResponseEntity resp = rentRequestClient.postRentRequest(dto, token);

        return resp;
    }

    @Override
    public ResponseEntity getRequests(boolean isEndUser, String token){
        List<ViewRequestDTO> reqs;
        List<AdDTO> adDTOS;
        List<Long> adIds;
        List<ReqAdDto> reqAdDTOS;

        if(token == null)
            return ResponseEntity.status(403).build();

        if(isEndUser)
            reqs = rentRequestClient.getEndUserRequests(token);
        else
            reqs = rentRequestClient.getAgentRequests(token);

        if(reqs == null)
            return ResponseEntity.status(403).build();

        adIds = getUniqueIds(reqs);

        adDTOS = adsClient.getAdsByIds(adIds, token);

        if(adDTOS == null)
            return ResponseEntity.status(403).build();

        reqAdDTOS = assemblePresentationDTO(reqs, adDTOS);
        return ResponseEntity.ok(reqAdDTOS);
    }

    public ResponseEntity pay(ReqIdDTO reqIdDTO, String token){
        ViewRequestDTO request = rentRequestClient.pay(reqIdDTO, token);

        List<RentAdDTO> rentAdDTOS = new ArrayList<>();

        for(AdDateRange adDateRange : request.getAdsWithDates()){
            rentAdDTOS.add(new RentAdDTO(adDateRange.getAd_id(), adDateRange.getStart(), adDateRange.getEnd()));
        }

        if(!adsClient.reserveDateForAd(rentAdDTOS, token))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }


    private List<Long> getUniqueIds(List<ViewRequestDTO> viewRequestDTOS) {
        List<Long> ids = new ArrayList<>();

        for(ViewRequestDTO v : viewRequestDTOS){
            for(AdDateRange ad: v.getAdsWithDates()) {
                ids.add(ad.getAd_id());
            }
        }

        return ids.stream().distinct().collect(Collectors.toList());
    }

    private List<ReqAdDto> assemblePresentationDTO(List<ViewRequestDTO> viewRequestDTOS, List<AdDTO> adDTOS){
        List<ReqAdDto> view = new ArrayList<>();

        for(ViewRequestDTO v : viewRequestDTOS){
            ReqAdDto req = new ReqAdDto(v);
            for(AdDateRange adDateRange : v.getAdsWithDates()){
                for(AdDTO adDTO : adDTOS){
                    if(adDTO.getId() == adDateRange.getAd_id()) {
                        req.getAds().add(new AdDTO(adDTO, adDateRange.getStart(), adDateRange.getEnd()));
                    }
                }
            }
            if(req.getAds().size() > 0)
                view.add(req);
        }

        return view;
    }
}
