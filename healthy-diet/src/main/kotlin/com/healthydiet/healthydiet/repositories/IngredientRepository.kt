package com.healthydiet.healthydiet.repositories

import com.healthydiet.healthydiet.models.Ingredient
import org.springframework.data.repository.CrudRepository

interface IngredientRepository : CrudRepository<Ingredient, Long> {
}