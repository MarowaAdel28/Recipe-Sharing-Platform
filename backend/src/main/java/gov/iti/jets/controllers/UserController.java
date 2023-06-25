package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.UserDTO;
import gov.iti.jets.models.dtos.profile.UserProfile;
import gov.iti.jets.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String save(@Valid @RequestBody UserDTO userDto) {
        System.out.println("user controller");
        return userService.save(userDto).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @PostMapping("/edit")
    public void edit(@Valid @RequestBody UserProfile userDto) {
        userService.update(userDto);
    }
    @PutMapping
    public void update(@Valid @RequestBody UserProfile userDto) {
        userService.update(userDto);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userService.getById(id);
    }

//    @GetMapping
//    public Page<UserDTO> query(@Valid UserQueryVO userDto) {
//        return userService.query(userDto);
//    }
}
