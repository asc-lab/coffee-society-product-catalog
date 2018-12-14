package pl.altkom.coffee.productcatalog.domain.query

import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
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
}