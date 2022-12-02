package de.wbh.sbmbackend.repository

import de.wbh.sbmbackend.entity.StorageBox
import org.springframework.data.jpa.repository.JpaRepository

interface BoxRepository : JpaRepository<StorageBox, Long> {

}