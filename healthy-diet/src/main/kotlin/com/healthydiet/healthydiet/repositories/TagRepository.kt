package com.healthydiet.healthydiet.repositories

import com.healthydiet.healthydiet.models.Tag
import org.springframework.data.repository.CrudRepository

interface TagRepository : CrudRepository<Tag, Int>