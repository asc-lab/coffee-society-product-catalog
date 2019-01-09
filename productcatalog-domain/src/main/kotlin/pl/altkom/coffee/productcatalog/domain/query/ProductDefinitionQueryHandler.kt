package pl.altkom.coffee.productcatalog.domain.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import pl.altkom.coffee.productcatalog.api.dto.ProductDefinitionDto
import pl.altkom.coffee.productcatalog.api.dto.ProductResourceDto
import pl.altkom.coffee.productcatalog.api.query.ProductDetailsQuery
import pl.altkom.coffee.productcatalog.api.query.ProductNameQuery
import pl.altkom.coffee.productcatalog.domain.repository.ProductDefinitionRepository

@Component
class ProductDefinitionQueryHandler(val repository: ProductDefinitionRepository) {

    @QueryHandler
    fun on(query: ProductNameQuery): String {
        return repository.findById(query.id).map {
            it.name
        }.get()
    }

    @QueryHandler
    fun on(query: ProductDetailsQuery): ProductDefinitionDto {
        return repository.findById(query.id).map {
            val resources = it.resources.map { resource -> ProductResourceDto(resource.type, resource.quantity) }.toList()
            ProductDefinitionDto(it.id!!, it.name, resources, it.tax)
        }.get()
    }
}