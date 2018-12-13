package pl.altkom.coffee.productcatalog.domain.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import pl.altkom.coffee.productcatalog.domain.model.ProductDefinition

@Repository
interface ProductDefinitionRepository : MongoRepository<ProductDefinition, String> {

    @Query("{'active': true}")
    fun findAllActiveProduct(): List<ProductDefinition>
}


