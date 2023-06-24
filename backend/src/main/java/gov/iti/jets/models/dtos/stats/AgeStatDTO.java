package gov.iti.jets.models.dtos.stats;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AgeStatDTO implements Serializable {
    private Integer youngAdults;
    private Integer middleAged;
    private Integer old;


}
