package de.wbh.sbmbackend.controller

import de.wbh.sbmbackend.entity.StorageBox
import de.wbh.sbmbackend.service.BoxService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class BoxController {

    @Autowired
    lateinit var boxService: BoxService

    @GetMapping("/boxes")
    fun findAllBoxes() : Iterable<StorageBox> {
        return boxService.findAllByOrderByAddedAtDesc()
    }

    @GetMapping("/boxes/{id}")
    fun findBox(@PathVariable id: Long) = boxService.findByID(id)

    @PostMapping("/boxes")
    fun addBox(@RequestBody box: StorageBox) {
        boxService.createBox(box)
    }

    @PutMapping("/boxes/{id}")
    fun updateBox(
        @PathVariable id: Long, @RequestBody box: StorageBox
    ) = boxService.updateBox(id, box)

    @DeleteMapping("/boxes/{id}")
    fun deleteBox(@PathVariable id: Long) = boxService.deleteBox(id)
}