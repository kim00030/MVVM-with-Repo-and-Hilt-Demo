package com.dan.mvvmwithrepoandhiltdemo.util

import com.dan.mvvmwithrepoandhiltdemo.model.Blog
import com.dan.mvvmwithrepoandhiltdemo.retrofit.BlogNetworkEntity
import javax.inject.Inject

/**
 * Created by Dan Kim
 */
class NetworkMapper @Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {

    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            category = domainModel.category,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog> {
        return entities.map {
            mapFromEntity(it)
        }
    }

}