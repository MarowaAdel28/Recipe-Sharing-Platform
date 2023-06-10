package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

}
