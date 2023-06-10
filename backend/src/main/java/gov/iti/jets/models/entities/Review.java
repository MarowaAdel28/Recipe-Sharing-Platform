package gov.iti.jets.models.entities;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "recipe_id", nullable = false)
    private Integer recipeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

}
