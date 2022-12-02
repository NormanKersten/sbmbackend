package de.wbh.sbmbackend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "items")
data class Item (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "box_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var box: StorageBox?,

    @Column(length = 50, nullable = false, unique = true)
    var benennung: String,

    @Column(length = 255)
    var beschreibung: String,

    @Column(name = "foto")
    var foto: String?,

    @Column(nullable = false)
    var anzahl: Int, )