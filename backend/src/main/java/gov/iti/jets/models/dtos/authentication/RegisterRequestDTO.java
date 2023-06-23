package gov.iti.jets.models.dtos.authentication;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO implements Serializable {
    private String userName;

    private String email;

    private String password;

    private Integer id;

    private Integer age;

    private Character gender;
}
