package com.pengcheng.domain;

public class Equipment {

    private Long id;
    private Long areaId;
    private Long areaSonId;
    private String profession;
    private String grade;
    private String meGroup;
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getAreaSonId() {
        return areaSonId;
    }

    public void setAreaSonId(Long areaSonId) {
        this.areaSonId = areaSonId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMeGroup() {
        return meGroup;
    }

    public void setMeGroup(String meGroup) {
        this.meGroup = meGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
