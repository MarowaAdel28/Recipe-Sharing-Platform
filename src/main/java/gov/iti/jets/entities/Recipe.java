/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "recipe")
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RecipePK recipePK;
    @Column(name = "cooks_count")
    private Integer cooksCount;
    @Basic(optional = false)
    @Lob
    @Column(name = "steps")
    private byte[] steps;
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
    private long preparingTime;
    @Basic(optional = false)
    @Column(name = "persons")
    private int persons;
    @ManyToMany(mappedBy = "recipeList")
    private List<Ingredients> ingredientsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<FavoriteRecipe> favoriteRecipeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Review> reviewList;
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recipe")
    private RejectedRecipe rejectedRecipe;

    public Recipe() {
    }

    public Recipe(RecipePK recipePK) {
        this.recipePK = recipePK;
    }

    public Recipe(RecipePK recipePK, byte[] steps, Date date, long preparingTime, int persons) {
        this.recipePK = recipePK;
        this.steps = steps;
        this.date = date;
        this.preparingTime = preparingTime;
        this.persons = persons;
    }

    public Recipe(int id, int userId, int categoryId) {
        this.recipePK = new RecipePK(id, userId, categoryId);
    }

    public RecipePK getRecipePK() {
        return recipePK;
    }

    public void setRecipePK(RecipePK recipePK) {
        this.recipePK = recipePK;
    }

    public Integer getCooksCount() {
        return cooksCount;
    }

    public void setCooksCount(Integer cooksCount) {
        this.cooksCount = cooksCount;
    }

    public byte[] getSteps() {
        return steps;
    }

    public void setSteps(byte[] steps) {
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

    public long getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(long preparingTime) {
        this.preparingTime = preparingTime;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        hash += (recipePK != null ? recipePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.recipePK == null && other.recipePK != null) || (this.recipePK != null && !this.recipePK.equals(other.recipePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gov.iti.jets.entities.Recipe[ recipePK=" + recipePK + " ]";
    }
    
}
