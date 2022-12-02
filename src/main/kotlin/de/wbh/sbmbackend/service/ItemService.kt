package de.wbh.sbmbackend.service

import de.wbh.sbmbackend.entity.Item
import de.wbh.sbmbackend.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ItemService {

    @Autowired
    lateinit var itemRepository: ItemRepository

    fun findAllByOrderByAddedAtDesc(): Iterable<Item> {
        return itemRepository.findAll()
    }

    fun findByID(id: Long): Item = itemRepository.findByIdOrNull(id) ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun createItem(item: Item) {
        itemRepository.save(item)
    }

    fun updateItem(id: Long, item: Item): Item {
        return if (itemRepository.existsById(item.id!!)) {
            itemRepository.save(item)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun deleteItem(id: Long) {
        if (itemRepository.existsById(id)) itemRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun itemsInBox(boxID: Long): ArrayList<Item> {
        val itemsList: ArrayList<Item> = itemRepository.findAll() as ArrayList<Item>
        val tempItemsList: ArrayList<Item> = arrayListOf()
        for (item in itemsList) {
            if (item.box?.id == boxID)
                tempItemsList.add(item)
        }
        return tempItemsList
    }

    fun searchInBox(boxID: Long, name: String, desc: String): ArrayList<Item> {
        val itemsList: ArrayList<Item> = itemRepository.findAll() as ArrayList<Item>
        val tempItemsList: ArrayList<Item> = arrayListOf()
        for (item in itemsList) {
            if (item.box?.id == boxID && item.benennung.contains(name, ignoreCase = true) &&
                item.beschreibung.contains(desc, ignoreCase = true))
                tempItemsList.add(item)
        }
        return tempItemsList
    }
}