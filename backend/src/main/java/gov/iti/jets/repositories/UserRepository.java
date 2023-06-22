package gov.iti.jets.repositories;

import gov.iti.jets.models.dtos.stats.AgeStatDTO;
import gov.iti.jets.models.dtos.stats.GenderStatDTO;
import gov.iti.jets.models.dtos.stats.RegistrationDateStatDTO;
import gov.iti.jets.models.dtos.stats.UserStatDTO;
import gov.iti.jets.models.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true)
    GenderStatDTO findGenderCount();
    @Query(nativeQuery = true)
    AgeStatDTO findAgeCount();
    @Query(nativeQuery = true)
    RegistrationDateStatDTO findRegisterDateCount();
    @Query("from User u")
    List<UserStatDTO> getUsers(PageRequest pageRequest);


}