package com.example.jpa.control.dto;

public class UserDto {

        private static final long serialVersionUID = 5315663631950061118L;


        private String id;

        private Boolean deleted;

        private String creatorId;

        private String createTime;

        private String updaterId;

        private String updateTime;

        private String cityCode;

        private String departmentId;

        private String name;

        private String mobile;

        private String idCard;

        private String password;

        private Boolean authorised;

        private Boolean adminEnabled = false;

        private String cityName;

        private String department;

        private String token;

        private int duty;

        private String inspectionAreaId;

        private Boolean inspectEnable = false;

        private Boolean outsideOffice = false;

        public UserDto() {
        }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getAuthorised() {
        return authorised;
    }

    public Boolean getOutsideOffice() {
        return outsideOffice;
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
        return "UserDto{" +
                "id='" + id + '\'' +
                ", deleted=" + deleted +
                ", creatorId='" + creatorId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updaterId='" + updaterId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idCard='" + idCard + '\'' +
                ", password='" + password + '\'' +
                ", authorised=" + authorised +
                ", adminEnabled=" + adminEnabled +
                ", cityName='" + cityName + '\'' +
                ", department='" + department + '\'' +
                ", token='" + token + '\'' +
                ", duty=" + duty +
                ", inspectionAreaId='" + inspectionAreaId + '\'' +
                ", inspectEnable=" + inspectEnable +
                ", outsideOffice=" + outsideOffice +
                '}';
    }
}
