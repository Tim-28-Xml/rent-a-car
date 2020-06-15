package com.tim26.RentRequestService.model;

import javax.persistence.*;
import java.time.LocalDateTime;

public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private RequestStatus requestStatus;

    @ManyToOne
    private User user;

    @Column
    private LocalDateTime creationTime;

    @Column
    private LocalDateTime reservationTime;

    public RentRequest() {
        requestStatus = RequestStatus.PENDING;
    }
}
