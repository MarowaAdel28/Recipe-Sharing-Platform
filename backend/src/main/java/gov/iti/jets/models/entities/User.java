/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import gov.iti.jets.listeners.UserListener;
import gov.iti.jets.util.Utility;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gov.iti.jets.models.dtos.stats.AgeStatDTO;
import gov.iti.jets.models.dtos.stats.GenderStatDTO;
import gov.iti.jets.models.dtos.stats.RegistrationDateStatDTO;

/**
 *
 * @author dell
 */
@EntityListeners(UserListener.class)
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NamedNativeQueries(
        {@NamedNativeQuery(name = "User.findGenderCount",
                query = "Select SUM(CASE WHEN u.gender = 'F' THEN 1 ELSE 0 END) AS femalesCount, " +
                        "SUM(CASE WHEN u.gender = 'M' THEN 1 ELSE 0 END) AS malesCount " +
                        "from User u",
                resultSetMapping = "Mapping.GenderStatDTO"),
        @NamedNativeQuery(name = "User.findAgeCount",
                query = "SELECT SUM(CASE WHEN u.age between 17 and 30 THEN 1 ELSE 0 END) AS youngAdults," +
                        "SUM(CASE WHEN u.age BETWEEN 31 AND 45 THEN 1 ELSE 0 END) AS middleAged," +
                        "SUM(CASE WHEN u.age > 45 THEN 1 ELSE 0 END) AS old " +
                        "FROM User u",
                resultSetMapping = "Mapping.AgeStatDTO"),

        @NamedNativeQuery(name = "User.findRegisterDateCount",
                query = "SELECT Sum(CASE WHEN timestampdiff(Month, u.create_time, now() ) between 0 and 6 then 1 else 0 end) AS thirdSixMonths,\n" +
                        " Sum(CASE WHEN  timestampdiff(Month, u.create_time, now() ) between 7 and 12 then 1 else 0 end) AS secondSixMonths,\n" +
                        " Sum(CASE WHEN timestampdiff(Month, u.create_time, now() ) between 13 and 18 then 1 else 0 end) AS firstSixMonths,\n" +
                        " Sum(CASE WHEN timestampdiff(Month, u.create_time, now() )> 18 then 1 else 0 end) AS earlier\n" +
                        " FROM User u",
                resultSetMapping = "Mapping.RegistrationStatDTO")
        }

)
@SqlResultSetMappings(
        {@SqlResultSetMapping(name = "Mapping.GenderStatDTO",
                classes = @ConstructorResult(targetClass = GenderStatDTO.class,
                        columns = {@ColumnResult(name = "femalesCount", type = Integer.class),
                                @ColumnResult(name = "malesCount", type = Integer.class)})),

        @SqlResultSetMapping(name = "Mapping.AgeStatDTO",
                classes = @ConstructorResult(targetClass = AgeStatDTO.class,
                        columns = {@ColumnResult(name = "youngAdults", type = Integer.class),
                                @ColumnResult(name = "middleAged", type = Integer.class),
                                @ColumnResult(name = "old", type = Integer.class)})),

        @SqlResultSetMapping(name = "Mapping.RegistrationStatDTO",
                classes = @ConstructorResult(targetClass = RegistrationDateStatDTO.class,
                        columns = {@ColumnResult(name = "firstSixMonths", type = Integer.class),
                                @ColumnResult(name = "secondSixMonths", type = Integer.class),
                                @ColumnResult(name = "thirdSixMonths", type = Integer.class),
                                @ColumnResult(name = "earlier", type = Integer.class)
                        })),

        })

public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "username")
    private String userName;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
//    @Column(nullable = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @Column(name = "gender")
    private Character gender;
    @Basic(optional = false)
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Basic(optional = false)
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<FavoriteRecipe> favoriteRecipeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Review> reviewList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Recipe> recipeList;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String userName, String email, String password, Date createTime, int age, Character gender, boolean isAdmin, boolean isDeleted) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createTime = createTime;
        this.age = age;
        this.gender = gender;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isAdmin){
            return List.of(new SimpleGrantedAuthority(Utility.USER_ROLE));
        }
        return List.of(new SimpleGrantedAuthority(Utility.USER_ROLE));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<FavoriteRecipe> getFavoriteRecipeList() {
        return favoriteRecipeList;
    }

    public void setFavoriteRecipeList(List<FavoriteRecipe> favoriteRecipeList) {
        this.favoriteRecipeList = favoriteRecipeList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.models.entities.User[ id=" + id + " ]";
    }
    
}
