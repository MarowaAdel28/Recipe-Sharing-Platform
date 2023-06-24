package gov.iti.jets.repositories;

import gov.iti.jets.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("update User u set u.username = :name where u.id = :id ")
    void updateUser(
            @Param("name") String name ,
            @Param("id") int id);
}