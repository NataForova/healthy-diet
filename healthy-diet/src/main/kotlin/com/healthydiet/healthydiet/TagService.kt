package com.healthydiet.healthydiet

import com.healthydiet.healthydiet.models.Tag
import com.healthydiet.healthydiet.models.TagRepository
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class TagService (private val db: TagRepository) {
    fun findTags() : List<Tag> = db.findAll().toList()

    fun findTagById(id: Int) : Tag = db.findById(id).orElseThrow { IOException("Can't find tag with $id")}
}