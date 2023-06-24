package gov.iti.jets.models.dtos.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements Serializable {

    private String userName;

    @JsonProperty("user")
    private Integer id;
}
