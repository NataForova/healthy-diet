package com.healthydiet.healthydiet.models

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

import javax.persistence.*

@Table("recipe")
class Recipe(val name: String, imageLink: String, text: String, cookingTime: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_SEQUENCE")
    var id: Long? = null
    private set

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    var tags: MutableList<Tag> = mutableListOf()

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    var ingridients: MutableList<Ingredient> = mutableListOf();

}
