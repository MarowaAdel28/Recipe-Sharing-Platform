package gov.iti.jets.models.dtos;


import gov.iti.jets.models.dtos.RecipeDTO;

import java.util.List;

public class RecipeResponseDTO {

    private List<RecipeDTO> data;
    private long totalItems;

    // Getters and Setters for data and totalItems

    public List<RecipeDTO> getData() {
        return data;
    }

    public void setData(List<RecipeDTO> data) {
        this.data = data;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

}