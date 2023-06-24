package gov.iti.jets.services;

import gov.iti.jets.models.dtos.UserDTO;
import gov.iti.jets.models.dtos.profile.UserProfile;
import gov.iti.jets.models.entities.User;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.util.Utility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        user.setCreateTime(new Date());
        user = userRepository.save(user);
        return user.getId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(UserProfile userDto) {
//        User user = requireOne(userDto.getId());
        System.out.println("userDto = " + userDto);
        userRepository.updateUser(userDto.getUserName(), userDto.getId());
        System.out.println("user = " + requireOne(userDto.getId()));
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


    public User getReference(Integer id) {
        return userRepository.getReferenceById(id);
    }
}
