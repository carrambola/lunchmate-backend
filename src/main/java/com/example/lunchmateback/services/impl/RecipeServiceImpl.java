package com.example.lunchmateback.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.lunchmateback.dtos.RecipeDto;
import com.example.lunchmateback.dtos.RecipeIngridientDto;
import com.example.lunchmateback.models.Category;
import com.example.lunchmateback.models.Ingridient;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.RecipeIngridient;
import com.example.lunchmateback.models.RecipeIngridientId;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.CategoryRepository;
import com.example.lunchmateback.repositories.IngridientRepository;
import com.example.lunchmateback.repositories.RecipeIngridientRepository;
import com.example.lunchmateback.repositories.RecipesRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.RecipeService;
import com.example.lunchmateback.services.UserDetailsImpl;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IngridientRepository ingridientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeIngridientRepository recipeIngridientRepository;

    @Override
    @Transactional
    public RecipeDto getRecipe(Long id) {
        Optional<Recipe> oRecipe = recipesRepository.findById(id);
        if (!oRecipe.isPresent()) {
            return null;
        }

        RecipeDto x = new RecipeDto();
        x.setCategoryId(oRecipe.get().getCategory().getId());
        x.setDescription(oRecipe.get().getDescription());
        x.setId(oRecipe.get().getId());
        x.setName(oRecipe.get().getName());
        x.setUserId(oRecipe.get().getUser().getId());
        x.setTime(oRecipe.get().getTime());
        x.setImage(oRecipe.get().getImage());
        x.setDifficulty(translateDifficulity(oRecipe.get().getDifficulty()));

        //Recipe recipe = oRecipe.get();
        //Hibernate.initialize(recipe.getIngridients());

        for (RecipeIngridient ri : recipeIngridientRepository.findByRecipe(oRecipe.get())) {
            RecipeIngridientDto riDto = new RecipeIngridientDto();
            riDto.setAmount(ri.getAmount());
            riDto.setIngridientId(ri.getId().getIngridientId());
            riDto.setRecipeId(ri.getId().getRecipeId());
            riDto.setName(ri.getIngridient().getName());
            riDto.setUnit(ri.getUnit());

            x.getIngridients().add(riDto);
        }

        Hibernate.initialize(oRecipe.get().getLikes());

        x.setLikes(oRecipe.get().getLikes());

        return x;
    }

    @Override
    // @Transactional
    public List<RecipeDto> getRecipes() {
        List<Recipe> recipes = (List<Recipe>) recipesRepository.findAll(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes) {
            // 

            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            x.setTime(r.getTime());
            x.setImage(r.getImage());
            x.setDifficulty(translateDifficulity(r.getDifficulty()));
            x.setLikes(r.getLikes());


            recipeDtos.add(x);
        }

        return recipeDtos;
    }

    @Override
    @Transactional
    public RecipeDto saveRecipe(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setDescription(dto.getDescription());
        recipe.setName(dto.getName());
        recipe.setCreatedAt(new Date());

        recipe.setLikes(0L);


        // kategoria
        Optional<Category> oCategory = categoryRepository.findById(dto.getCategoryId());
        if (!oCategory.isPresent()) {
            return null;
        }

        recipe.setCategory(oCategory.get());

        // author - uzyskanie uzytkownika
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oUser = userRepository.findById(userDetails.getId());
        if (!oUser.isPresent()) {
            return null;
        }
        recipe.setUser(oUser.get());


        // recipe.setIngridients(ingridients);

        recipe.setImage(dto.getImage());

        recipe.setTime(dto.getTime());

        recipe.setDifficulty(translateDifficulity(dto.getDifficulty()));

        recipe = recipesRepository.save(recipe);

        // dodaje skladniki
        // List<RecipeIngridient> ingridients = new ArrayList<>();
        for (RecipeIngridientDto riDto : dto.getIngridients()) {
            Optional<Ingridient> oIngridient = ingridientRepository.findById(riDto.getIngridientId());
            if (oIngridient.isPresent()) {
                RecipeIngridient ri = new RecipeIngridient();
                RecipeIngridientId id = new RecipeIngridientId(recipe.getId(), oIngridient.get().getId());

                ri.setAmount(riDto.getAmount());
                ri.setId(id);
                ri.setUnit(riDto.getUnit());
                ri.setRecipe(recipe);
                ri.setIngridient(oIngridient.get());

                recipeIngridientRepository.save(ri);
                // ri.setRecipe(recipe.getId());
                //ingridients.add(ri);
            }
        }

        // zwrotna odpowiedz
        return getRecipe(recipe.getId());
    }

    @Override
    public Boolean addLikeToRecipe(Long recipeId) {
        Optional<Recipe> oReOptional = recipesRepository.findById(recipeId);
        if (!oReOptional.isPresent()) {
            return false;
        }

        Recipe r = oReOptional.get();
        r.setLikes(r.getLikes() + 1);

        recipesRepository.save(r);
        return true;

    }

    @Override
    public List<RecipeDto> getMostLikedRecipes() {
        List<Recipe> recipes = (List<Recipe>) recipesRepository.findTop10ByOrderByLikesDesc(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            x.setTime(r.getTime());
            x.setImage(r.getImage());
            x.setDifficulty(translateDifficulity(r.getDifficulty()));
            x.setLikes(r.getLikes());
            recipeDtos.add(x);
        }

        return recipeDtos;
    }

    @Override
    @Transactional
    public RecipeDto update(RecipeDto dto, Long id) {
        Optional<Recipe> oRecipe = recipesRepository.findById(id);
        if (!oRecipe.isPresent()) {
            return null;
        }

        Recipe recipe = oRecipe.get();
        recipe.setDescription(dto.getDescription());
        recipe.setName(dto.getName());
        recipe.setCreatedAt(new Date());

        // kategoria
        Optional<Category> oCategory = categoryRepository.findById(dto.getCategoryId());
        if (!oCategory.isPresent()) {
            return null;
        }

        recipe.setCategory(oCategory.get());

        // author - uzyskanie uzytkownika
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oUser = userRepository.findById(userDetails.getId());
        if (!oUser.isPresent()) {
            return null;
        }
        recipe.setUser(oUser.get());


        // recipe.setIngridients(ingridients);

        recipe.setImage(dto.getImage());

        recipe.setTime(dto.getTime());

        recipe.setDifficulty(translateDifficulity(dto.getDifficulty()));
        recipe = recipesRepository.save(recipe);


        // teraz zarzadzam składnikami - musze ponownie pobrac obiekt z bazy
        oRecipe = recipesRepository.findById(recipe.getId());
        recipe = oRecipe.get();
        recipeIngridientRepository.deleteByRecipe(recipe);

        // dodaje skladniki
        for (RecipeIngridientDto riDto : dto.getIngridients()) {
            Optional<Ingridient> oIngridient = ingridientRepository.findById(riDto.getIngridientId());
            if (oIngridient.isPresent()) {
                RecipeIngridient ri = new RecipeIngridient();
                RecipeIngridientId riId = new RecipeIngridientId(recipe.getId(), oIngridient.get().getId());

                ri.setAmount(riDto.getAmount());
                ri.setId(riId);
                ri.setUnit(riDto.getUnit());
                ri.setRecipe(recipe);
                ri.setIngridient(oIngridient.get());

                recipeIngridientRepository.save(ri);
            }
        }

        // zwrotna odpowiedz
        return getRecipe(recipe.getId());
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Recipe> orecipe = recipesRepository.findById(id);
        if (!orecipe.isPresent()) {
            return false;
        }
        recipesRepository.deleteById(id);
        return true;
    }

    @Override
    public Object getMostRecentRecipes() {
        List<Recipe> recipes = (List<Recipe>) recipesRepository.findTop10ByOrderByCreatedAtDesc(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            x.setTime(r.getTime());
            x.setImage(r.getImage());
            x.setDifficulty(translateDifficulity(r.getDifficulty()));

            x.setLikes(r.getLikes());
            recipeDtos.add(x);
        }

        return recipeDtos;
    }

    @Override
    public Object getMostEasyRecipes() {
        List<Recipe> recipes = (List<Recipe>) recipesRepository.findTop10ByOrderByDifficultyAsc(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            x.setTime(r.getTime());
            x.setImage(r.getImage());
            x.setDifficulty(translateDifficulity(r.getDifficulty()));

            x.setLikes(r.getLikes());
            recipeDtos.add(x);
        }

        return recipeDtos;
    }

    private String translateDifficulity(Integer difficulty) {
        switch (difficulty) {
            case 1:
                return "easy";
            case 2:
                return "medium";
            case 3:
                return "hard";
            default:
                return "stara nie podchodź";
        }
    }

    private Integer translateDifficulity(String difficulty) {
        if (difficulty.equals("easy")) return 1;
        if (difficulty.equals("medium")) return 2;
        if (difficulty.equals("hard")) return 3;
        return 0;
    }


}
