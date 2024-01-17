package com.example.lunchmateback.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_ingridient")
@NoArgsConstructor
@Getter
@Setter
public class RecipeIngridient {
    @EmbeddedId
    private RecipeIngridientId id;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne
    @MapsId("ingridientId")
    private Ingridient ingridient;

    @NotNull
    @Min(value = 1)
    private Integer amount;

    @NotBlank
    private String unit;

    public RecipeIngridient(Recipe recipe, Ingridient ingridient) {
        this.recipe = recipe;
        this.ingridient = ingridient;
        this.id = new RecipeIngridientId(recipe.getId(), ingridient.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
        result = prime * result + ((ingridient == null) ? 0 : ingridient.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecipeIngridient other = (RecipeIngridient) obj;
        if (recipe == null) {
            if (other.recipe != null)
                return false;
        } else if (!recipe.equals(other.recipe))
            return false;
        if (ingridient == null) {
            if (other.ingridient != null)
                return false;
        } else if (!ingridient.equals(other.ingridient))
            return false;
        return true;
    }

    
}
