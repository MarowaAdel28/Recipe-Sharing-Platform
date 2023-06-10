package gov.iti.jets.models.entities;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cooks_count")
    private Integer cooksCount = 0;

    @Column(name = "steps", nullable = false)
    private String steps;

    @Column(name = "status")
    private String status = "waiting";

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "is_deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "preparing_time", nullable = false)
    private String preparingTime;

    @Column(name = "persons", nullable = false)
    private Integer persons;

}
