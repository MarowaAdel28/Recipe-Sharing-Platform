/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gov.iti.jets.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String recipeName;
    @Column(name = "cooks_count")
    private Integer cooksCount;
    @Basic(optional = false)
    @Lob
    @Column(name = "steps")
    private String steps;
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Basic(optional = false)
    @Column(name = "preparing_time")
    private String preparingTime;
    @Basic(optional = false)
    @Column(name = "persons")
    private int persons;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private List<Image> imageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private List<FavoriteRecipe> favoriteRecipeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId")
    private List<Review> reviewList;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category categoryId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<RecipeHasIngredients> recipeHasIngredientsList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
    private RejectedRecipe rejectedRecipe;

    public Recipe() {
    }

    public Recipe(Integer id) {
        this.id = id;
    }

    public Recipe(Integer id, String steps, Date date, String preparingTime, int persons) {
        this.id = id;
        this.steps = steps;
        this.date = date;
        this.preparingTime = preparingTime;
        this.persons = persons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCooksCount() {
        return cooksCount;
    }

    public void setCooksCount(Integer cooksCount) {
        this.cooksCount = cooksCount;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(String preparingTime) {
        this.preparingTime = preparingTime;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
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

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<RecipeHasIngredients> getRecipeHasIngredientsList() {
        return recipeHasIngredientsList;
    }

    public void setRecipeHasIngredientsList(List<RecipeHasIngredients> recipeHasIngredientsList) {
        this.recipeHasIngredientsList = recipeHasIngredientsList;
    }

    public RejectedRecipe getRejectedRecipe() {
        return rejectedRecipe;
    }

    public void setRejectedRecipe(RejectedRecipe rejectedRecipe) {
        this.rejectedRecipe = rejectedRecipe;
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
        if (!(object instanceof Recipe other)) {
            return false;
        }
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "gov.iti.jets.models.entities.Recipe[ id=" + id + " ]";
    }
    
}
