package com.tim26.Zuul.zuulserver.service;

import com.tim26.Zuul.zuulserver.client.AdsClient;
import com.tim26.Zuul.zuulserver.client.RentRequestClient;
import com.tim26.Zuul.zuulserver.dto.AdDTO;
import com.tim26.Zuul.zuulserver.dto.AdDateRange;
import com.tim26.Zuul.zuulserver.dto.ReqAdDto;
import com.tim26.Zuul.zuulserver.dto.ViewRequestDTO;
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
    public ResponseEntity getRequests(boolean isEndUser, String token){
        List<ViewRequestDTO> reqs;
        List<AdDTO> adDTOS;
        List<Long> adIds = new ArrayList<>();
        List<ReqAdDto> reqAdDTOS = new ArrayList<>();

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
                    if(adDTO.getId() == adDateRange.getAd_id())
                        req.getAds().add(adDTO);
                }
            }
            if(req.getAds().size() > 0)
                view.add(req);
        }

        return view;
    }
}
