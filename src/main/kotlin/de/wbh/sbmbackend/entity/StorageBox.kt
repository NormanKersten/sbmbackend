package de.wbh.sbmbackend.entity

import javax.persistence.*

@Entity
@Table(name = "boxes")
data class StorageBox (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(length = 50, nullable = false, unique = true)
    var benennung: String,

    @Column(length = 250)
    var beschreibung: String,

)
