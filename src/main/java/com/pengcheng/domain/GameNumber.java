package com.pengcheng.domain;

public class GameNumber {

    private Long id;
    private Long areaId;
    private Long areaSonId;
    private String profession;
    private Boolean isBetter;
    private Integer level;

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

    public Boolean getBetter() {
        return isBetter;
    }

    public void setBetter(Boolean better) {
        isBetter = better;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
