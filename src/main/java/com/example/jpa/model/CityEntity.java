package com.example.jpa.model;

import com.example.jpa.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mars on 2016/11/3.
 */
@Entity
@Table(name = "sys_city_info")
public class CityEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "city_code")
    private long code;

    @Column(name = "city_name")
    private String name;

    @Column(name = "brief_pin")
    private String briefPin;

    @JsonIgnore
    @Column(name = "is_deleted")
    private boolean deleted;

    public CityEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBriefPin() {
        return briefPin;
    }

    public void setBriefPin(String briefPin) {
        this.briefPin = briefPin;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
