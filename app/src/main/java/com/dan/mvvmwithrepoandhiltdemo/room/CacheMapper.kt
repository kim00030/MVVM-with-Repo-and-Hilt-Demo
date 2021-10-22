package com.dan.mvvmwithrepoandhiltdemo.room

import com.dan.mvvmwithrepoandhiltdemo.model.Blog
import com.dan.mvvmwithrepoandhiltdemo.util.EntityMapper
import javax.inject.Inject

/**
 * Created by Dan Kim
 */
class CacheMapper @Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {

    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            category = domainModel.category,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog> {
        return entities.map {
            mapFromEntity(it)
        }
    }

}