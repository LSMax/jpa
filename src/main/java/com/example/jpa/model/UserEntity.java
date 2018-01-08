package com.example.jpa.model;

import com.example.jpa.model.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Mars on 2017/8/29.
 */
@Entity
@Table(name = "sys_user_info")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 5315663631950061118L;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_mobile")
    private String mobile;

    @Column(name = "user_idcard")
    private String idCard;

    @JsonIgnore
    @Column(name = "user_password")
    private String password;

    @Column(name = "is_authorised")
    private Boolean authorised;

    @Column(name = "is_admin_enable")
    private Boolean adminEnabled = false;

    @Transient
    private String cityName;

    @Transient
    private String department;

    @Transient
    private String token;

    @Column(name = "duty")
    private int duty;

    @Column(name = "inspection_area_id")
    private String inspectionAreaId;

    @Column(name = "is_inspect_enable")
    private Boolean inspectEnable = false;

    @Column(name = "is_outside_office")
    private Boolean outsideOffice = false;

    public UserEntity() {
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(Boolean authorised) {
        this.authorised = authorised;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAdminEnabled() {
        return adminEnabled;
    }

    public void setAdminEnabled(Boolean adminEnabled) {
        this.adminEnabled = adminEnabled;
    }

    public int getDuty() {
        return duty;
    }

    public void setDuty(int duty) {
        this.duty = duty;
    }

    public String getInspectionAreaId() {
        return inspectionAreaId;
    }

    public void setInspectionAreaId(String inspectionAreaId) {
        this.inspectionAreaId = inspectionAreaId;
    }

    public Boolean getInspectEnable() {
        return inspectEnable;
    }

    public void setInspectEnable(Boolean inspectEnable) {
        this.inspectEnable = inspectEnable;
    }

    public Boolean isOutsideOffice() {
        return outsideOffice;
    }

    public void setOutsideOffice(Boolean outsideOffice) {
        this.outsideOffice = outsideOffice;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "cityCode='" + cityCode + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idCard='" + idCard + '\'' +
                ", authorised=" + authorised +
                ", adminEnabled=" + adminEnabled +
                ", deleted=" + isDeleted() +
                ",duty=" + duty +
                ",inspectionAreaId=" + inspectionAreaId +
                ",inspectEnable=" + inspectEnable +
                ",isOutsideOffice=" + outsideOffice +
                '}';
    }
}

