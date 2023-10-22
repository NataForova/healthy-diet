package com.healthydiet.healthydiet.controller

import com.healthydiet.healthydiet.models.Recipe
import com.healthydiet.healthydiet.models.response.ResponseBase
import com.healthydiet.healthydiet.service.RecipeService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/recipes")
class RecipeController(private val recipeService: RecipeService) {
    @GetMapping("/")
    fun getListRecipe(@RequestParam page: Int,
                      @RequestParam limit: Int,
                      @RequestParam isFavorite: Int?,
                      @RequestParam isInShoppingCart: Int?,
                      @RequestParam author: Int?,
                      @RequestParam tags: List<Int?>,
    ) : ResponseBase<Recipe> = recipeService.listRecipe(page, limit, isFavorite, isInShoppingCart, author, tags)


}
