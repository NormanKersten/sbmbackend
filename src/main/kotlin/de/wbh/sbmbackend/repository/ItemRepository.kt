package de.wbh.sbmbackend.repository

import de.wbh.sbmbackend.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {

}