package com.healthydiet.healthydiet

import com.healthydiet.healthydiet.models.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.http.HttpResponse

@RestController
@RequestMapping("/tags")
class TagsController(val service: TagService) {

    @GetMapping("/")
    fun list() : List<Tag> = service.findTags()

    @GetMapping("/{id}")
    fun tag(@PathVariable id: Int) : ResponseEntity<Tag> = ResponseEntity.ok(service.findTagById(id))
}