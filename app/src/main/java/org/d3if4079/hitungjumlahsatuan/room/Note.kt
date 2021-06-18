package org.d3if4079.hitungjumlahsatuan.room

import androidx.room.*

@Entity

data class Note (@PrimaryKey(autoGenerate = true)  val id : Int, val note: String)




