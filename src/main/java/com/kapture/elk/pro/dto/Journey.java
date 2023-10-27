package com.kapture.elk.pro.dto;

import lombok.Data;

import javax.persistence.*;


@Table
@Entity(name = "journey")
public @Data class Journey extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "client_id")
    long clientId;

    @Column(name = "journey_name")
    String journeyName;

    @Column(name = "event_name")
    String eventNames;

    @Column(name = "details")
    String details;

}
