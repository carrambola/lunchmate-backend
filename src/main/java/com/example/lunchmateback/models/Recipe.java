package com.example.lunchmateback.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Date createdAt;

    private Date prepareTime;
    private Double avgRating;
    private Long diffLevel;

    @NotNull
    @Column(length = 2000)
    private String description;

    // to mowi ktore zdjecie pokazac - z gotowych np. 10 zdjec w folderze static
    private String pictureName;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "recipe")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    // TODO: zdjecie
    // TODO: skladniki

    @OneToMany(
        mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @ManyToMany
    private Set<User> likes = new HashSet<>();

}
