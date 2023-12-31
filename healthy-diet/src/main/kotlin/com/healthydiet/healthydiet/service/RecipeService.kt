package com.healthydiet.healthydiet.service

import com.healthydiet.healthydiet.models.Recipe
import com.healthydiet.healthydiet.models.response.ResponseBase
import com.healthydiet.healthydiet.repositories.RecipeRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam

@Service
data class RecipeService(private val recipeRepository: RecipeRepository) {
    fun listRecipe(page: Int,
                   limit: Int,
                   isFavorite: Int?,
                   isInShoppingCart: Int?,
                   author: Int?,
                   tags: List<Int?>) : ResponseBase<Recipe> {
        val pageable: Pageable = PageRequest.of(page, limit)
        val tmp =  recipeRepository.findAllBy(pageable).toList()
        var response = ResponseBase<Recipe>(tmp.size, "", "")
        response.results = tmp
        return response
    }
}
