package com.dss.dss4msmoviev1.dto;

public class UpdateMovieDTO {
    private String image;
    private int cost;

    public UpdateMovieDTO() {
    }

    public UpdateMovieDTO(String image, int cost) {
        this.image = image;
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


}
