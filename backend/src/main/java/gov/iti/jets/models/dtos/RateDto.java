package gov.iti.jets.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto implements Serializable {

    private int rate;

    private int recipeId;

    private int userId;
}
