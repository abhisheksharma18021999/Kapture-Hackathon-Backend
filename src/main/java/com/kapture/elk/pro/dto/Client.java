package com.kapture.elk.pro.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table
@Entity(name = "client")
@ToString
public @Data
class Client extends BaseObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "client_name")
    String clientName;

    @Column(name = "plan_name")
    String planName;

    @Column(name = "email")
    String email;

    @Column(name = "meta_data")
    String metaData;

}
