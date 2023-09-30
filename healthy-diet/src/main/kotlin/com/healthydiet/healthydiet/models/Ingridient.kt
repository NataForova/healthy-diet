package com.healthydiet.healthydiet.models

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import org.springframework.data.relational.core.mapping.Table


@Table("ingredient")
class Ingredient(val name: String, val measurementUnit: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_SEQUENCE")
    var id: Long? = null
        private set
}
