package com.sapient.recipeapp.domain.util

import com.sapient.recipeapp.createRecipeItem
import com.sapient.recipeapp.createRecipeResponseItem
import org.junit.Test
import kotlin.test.assertEquals

internal class DataMapperTest {
    private val dataMapper = DataMapper()

    @Test
    fun testMapRecipeToDomain() {
        val repoItem = createRecipeResponseItem()
        val item = dataMapper.mapRecipeToDomain(repoItem)

        assertEquals(repoItem.id, item.id)
        assertEquals(repoItem.sourceName, item.sourceName)
    }

    @Test
    fun testMapDomainToEntity() {
        val repoItem = createRecipeItem()
        val item = dataMapper.mapDomainToEntity(repoItem)

        assertEquals(repoItem.id, item.id)
        assertEquals(repoItem.sourceName, item.sourceName)
    }
}