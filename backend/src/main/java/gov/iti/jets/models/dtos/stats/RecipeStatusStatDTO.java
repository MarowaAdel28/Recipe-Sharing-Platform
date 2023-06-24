package gov.iti.jets.models.dtos.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeStatusStatDTO implements Serializable {
    private Integer rejected;
    private Integer accepted;
    private Integer waiting;

}
