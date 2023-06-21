package gov.iti.jets.services;

import gov.iti.jets.models.dtos.UserDTO;
import gov.iti.jets.models.entities.User;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.util.Utility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer save(UserDTO userDto) {
        System.out.println("user save method invoked");
        System.out.println(userDto.getAge());
        User user = new User();
        userDto.setPassword(Utility.hashPassword(userDto.getPassword()));
        BeanUtils.copyProperties(userDto, user);
        user = userRepository.save(user);
        return user.getId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(UserDTO userDto) {
        User user = requireOne(userDto.getId());
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
    }

    public UserDTO getById(Integer id) {
        User user = requireOne(id);
        return toDTO(user);
    }

//    public Page<UserDTO> query(UserQueryVO userDto) {
//        throw new UnsupportedOperationException();
//    }

    private UserDTO toDTO(User user) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private User requireOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
