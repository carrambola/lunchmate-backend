package com.example.lunchmateback.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredientId implements Serializable {
    private Long recipeId;
    private Long ingredientId;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recipeId == null) ? 0 : recipeId.hashCode());
        result = prime * result + ((ingredientId == null) ? 0 : ingredientId.hashCode());
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
        RecipeIngredientId other = (RecipeIngredientId) obj;
        if (recipeId == null) {
            if (other.recipeId != null)
                return false;
        } else if (!recipeId.equals(other.recipeId))
            return false;
        if (ingredientId == null) {
            if (other.ingredientId != null)
                return false;
        } else if (!ingredientId.equals(other.ingredientId))
            return false;
        return true;
    }

    
}
