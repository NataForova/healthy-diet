package com.healthydiet.healthydiet.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository


@Table("tag")
data class Tag (@Id var id: Int, var name: String, var color: String, var slug: String?)

interface TagRepository : CrudRepository<Tag, Int>