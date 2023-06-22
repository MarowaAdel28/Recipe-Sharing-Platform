package gov.iti.jets.models.dtos.stats;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GenderStatDTO implements Serializable {
    private Integer femalesCount;
    private Integer malesCount;

}
