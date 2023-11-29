package com.example.core.Utils

import com.example.core.data.local.entity.BlogEntity
import com.example.core.data.local.entity.OwnerEntity
import com.example.core.data.network.model.BlogDTO
import com.example.core.data.network.model.OwnerDTO
import com.example.core.domain.model.Blog
import com.example.core.domain.model.Owner

fun List<BlogDTO>.toDomain(): List<Blog> {
    return map {
        Blog(
            id = it.id ?: "",
            image = it.image ?: "",
            likes = it.likes ?: 0,
            owner = it.owner?.toDomain() ?: Owner("", "", "", "", ""),
            publishDate = it.publishDate ?: "",
            tags = it.tags ?: emptyList(),
            text = it.text ?: "",
            isFavorite = false
        )
    }
}

fun List<BlogDTO>.toEntity(): List<BlogEntity> {
    return map {
        BlogEntity(
            id = it.id ?: "",
            image = it.image ?: "",
            likes = it.likes ?: 0,
            owner = it.owner?.toEntity() ?: OwnerEntity("", "", "", "", ""),
            publishDate = it.publishDate ?: "",
            tags = it.tags ?: emptyList(),
            text = it.text ?: ""
        )
    }
}

fun OwnerDTO.toEntity(): OwnerEntity {
    return OwnerEntity(
        firstName ?: "",
        id ?: "",
        lastName ?: "",
        picture ?: "",
        title ?: ""
    )
}

fun OwnerEntity.toDomain(): Owner {
    return Owner(
        firstName,
        id,
        lastName,
        picture,
        title
    )
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        firstName ?: "",
        id ?: "",
        lastName ?: "",
        picture ?: "",
        title ?: ""
    )
}

fun Owner.toEntity(): OwnerEntity {
    return OwnerEntity(
        firstName,
        id,
        lastName,
        picture,
        title
    )
}

object DataMapper {
    fun mapResponsesToEntities(input: List<BlogDTO?>?): List<BlogEntity> {
        val blogList = ArrayList<BlogEntity>()
        input?.map {
            val news = BlogEntity(
                id = it?.id ?: "",
                image = it?.image ?: "",
                likes = it?.likes ?: 0,
                owner = it?.owner?.toEntity() ?: OwnerEntity("", "", "", "", ""),
                publishDate = it?.publishDate ?: "",
                tags = it?.tags ?: emptyList(),
                text = it?.text ?: ""
            )
            blogList.add(news)
        }
        return blogList
    }

    fun mapEntitiesToDomain(input: List<BlogEntity>): List<Blog> =
        input.map {
            Blog(
                id = it.id,
                image = it.image,
                likes = it.likes,
                owner = it.owner.toDomain(),
                publishDate = it.publishDate,
                tags = it.tags,
                text = it.text,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Blog) = BlogEntity(
        id = input.id,
        image = input.image,
        likes = input.likes,
        owner = input.owner.toEntity(),
        publishDate = input.publishDate,
        tags = input.tags,
        text = input.text
    )
}