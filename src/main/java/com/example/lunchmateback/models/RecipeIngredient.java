package com.example.lunchmateback.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_ingredient")
@NoArgsConstructor
@Getter
@Setter
public class RecipeIngredient {
    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @NotNull
    @Min(value = 1)
    private Integer amount;

    @NotBlank
    private String scale;

    public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.id = new RecipeIngredientId(recipe.getId(), ingredient.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
        result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
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
        RecipeIngredient other = (RecipeIngredient) obj;
        if (recipe == null) {
            if (other.recipe != null)
                return false;
        } else if (!recipe.equals(other.recipe))
            return false;
        if (ingredient == null) {
            if (other.ingredient != null)
                return false;
        } else if (!ingredient.equals(other.ingredient))
            return false;
        return true;
    }

    
}
