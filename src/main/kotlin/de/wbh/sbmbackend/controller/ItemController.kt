package de.wbh.sbmbackend.controller

import de.wbh.sbmbackend.entity.Item
import de.wbh.sbmbackend.service.BoxService
import de.wbh.sbmbackend.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    lateinit var boxService: BoxService

    @GetMapping("/items")
    fun findAllItems() : Iterable<Item> {
        return itemService.findAllByOrderByAddedAtDesc()
    }

    @GetMapping("/items/{id}")
    fun findItem(@PathVariable id: Long) = itemService.findByID(id)

    @GetMapping("/itemsinbox/{id}")
    fun findItemsInBox(@PathVariable id: Long) = itemService.itemsInBox(id)

    @GetMapping("/itemssearch/{boxId}")
    fun searchItemsInBox(@PathVariable boxId: Long, @RequestParam name: String,
                         @RequestParam desc: String) = itemService.searchInBox(boxId, name, desc)

    @PostMapping("/items/{boxID}")
    fun addItem(@PathVariable boxID: Long, @RequestBody item: Item) {
        item.box = boxService.findByID(boxID)
        itemService.createItem(item)
    }

    @PutMapping("/items/{boxID}")
    fun updateItem(@PathVariable boxID: Long, @RequestBody item: Item) {
        item.box = boxService.findByID(boxID)
        itemService.updateItem(item.id!!, item)
    }

    @DeleteMapping("/items/{id}")
    fun deleteItem(@PathVariable id: Long) = itemService.deleteItem(id)
}