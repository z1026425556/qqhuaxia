package com.pengcheng.domain;

public class Eternal {

    private Long id;
    private Long areaId;
    private Long areaSonId;
    private String profession;
    private String category;
    private String categorySon;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategorySon() {
        return categorySon;
    }

    public void setCategorySon(String categorySon) {
        this.categorySon = categorySon;
    }
}
