package gov.iti.jets.models.dtos;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;

    private String email;

    private String password;

    private LocalDateTime createTime;

    private Integer id;

    private Integer age;

    private String gender;

    private Boolean admin;

    private Boolean deleted;

}
