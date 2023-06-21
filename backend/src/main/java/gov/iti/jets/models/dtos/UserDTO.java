package gov.iti.jets.models.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;

    private String email;

    private String password;

    private Integer id;

    private Integer age;

    private Character gender;


}
