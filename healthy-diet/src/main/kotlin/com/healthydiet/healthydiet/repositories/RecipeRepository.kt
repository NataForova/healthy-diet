package com.healthydiet.healthydiet.repositories

import com.healthydiet.healthydiet.models.Recipe
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface RecipeRepository: CrudRepository<Recipe, Long> {
    fun findAllBy(pageable: Pageable): Page<Recipe>
}