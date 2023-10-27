package com.kapture.elk.pro.dto;

import lombok.Data;
import javax.persistence.*;

@Table
@Entity(name = "events")
public @Data class Events extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "event_name")
    String eventName;

    @Column(name = "meta_data")
    String metaData;

    @Column(name = "client_id")
    long clientId;

    @Column(name = "customer_id")
    long customerId;

    @Column(name = "journey_id")
    long journeyId;

    @Column(name = "is_master_data")
    boolean isMasterData;

}
