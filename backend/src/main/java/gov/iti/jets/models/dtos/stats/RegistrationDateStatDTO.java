package gov.iti.jets.models.dtos.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegistrationDateStatDTO implements Serializable {

    private Integer firstSixMonths;
    private Integer secondSixMonths;
    private Integer thirdSixMonths;
    private Integer earlier;

}
