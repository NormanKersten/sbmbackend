package de.wbh.sbmbackend.service

import de.wbh.sbmbackend.entity.StorageBox
import de.wbh.sbmbackend.repository.BoxRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BoxService {

    @Autowired
    lateinit var boxRepository: BoxRepository

    fun findAllByOrderByAddedAtDesc(): Iterable<StorageBox> {
        return boxRepository.findAll()
    }

    fun findByID(id: Long): StorageBox = boxRepository.findByIdOrNull(id) ?:
        throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun createBox(box: StorageBox) {
        boxRepository.save(box)
    }

    fun updateBox(id: Long, box: StorageBox): StorageBox {
        return if (boxRepository.existsById(id)) {
            box.id = id
            boxRepository.save(box)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun deleteBox(id: Long) {
        if (boxRepository.existsById(id)) boxRepository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}