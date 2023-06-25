package gov.iti.jets.models.dtos.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.iti.jets.models.dtos.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecipeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private String status;

    private CategoryDTO category;

    private List<RateDto> rateList;
}
