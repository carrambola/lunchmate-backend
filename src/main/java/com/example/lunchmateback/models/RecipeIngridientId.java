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
public class RecipeIngridientId implements Serializable {
    private Long recipeId;
    private Long ingridientId;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recipeId == null) ? 0 : recipeId.hashCode());
        result = prime * result + ((ingridientId == null) ? 0 : ingridientId.hashCode());
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
        RecipeIngridientId other = (RecipeIngridientId) obj;
        if (recipeId == null) {
            if (other.recipeId != null)
                return false;
        } else if (!recipeId.equals(other.recipeId))
            return false;
        if (ingridientId == null) {
            if (other.ingridientId != null)
                return false;
        } else if (!ingridientId.equals(other.ingridientId))
            return false;
        return true;
    }

    
}
