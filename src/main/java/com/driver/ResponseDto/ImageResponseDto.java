package com.driver.ResponseDto;

public class ImageResponseDto {

    private int id;

    private String description;

    private String dimension;

    public ImageResponseDto(int id, String description, String dimension) {
        this.id = id;
        this.description = description;
        this.dimension = dimension;
    }

    public ImageResponseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
