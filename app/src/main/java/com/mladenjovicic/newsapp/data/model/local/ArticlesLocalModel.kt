package com.mladenjovicic.newsapp.data.model.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey



@Entity(tableName = "Articles", indices = [Index(value = arrayOf("url"), unique = true)])

data class ArticlesLocalModel(
    @ColumnInfo(name="author")
    var author:String?,
    @ColumnInfo(name ="title")
    var title:String?,
    @ColumnInfo(name ="description")
    var description:String?,
    @ColumnInfo(name ="publishedAt")
    var publishedAt:String?,
    @ColumnInfo(name ="content")
    var content:String?,
    @ColumnInfo(name ="url")
    var url:String?,
    @ColumnInfo(name ="urlToImage")
    var urlToImage:String?,
    @ColumnInfo(name ="sourceId")
    var sourceId:String?,
    @ColumnInfo(name ="sourceName")
    var sourceName:String?
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id:Int?=null
}
