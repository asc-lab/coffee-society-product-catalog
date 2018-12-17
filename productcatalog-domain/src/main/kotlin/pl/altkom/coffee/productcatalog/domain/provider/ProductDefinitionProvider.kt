package pl.altkom.coffee.productcatalog.domain.provider

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.altkom.coffee.productcatalog.api.dto.ProductDefinitionDto
import pl.altkom.coffee.productcatalog.api.dto.ProductResourceDto
import pl.altkom.coffee.productcatalog.domain.model.ProductDefinition
import pl.altkom.coffee.productcatalog.domain.repository.ProductDefinitionRepository
import kotlin.streams.toList

@Transactional(readOnly = true)
@Service
class ProductDefinitionProvider(val productDefinitionRepository: ProductDefinitionRepository) {

    fun findAllActiveProduct(): List<ProductDefinitionDto> {
        return productDefinitionRepository.findAllActiveProduct().stream().map {
            ProductDefinitionDto(it.id!!, it.name, mapProductResource(it), it.tax)
        }.toList()
    }

    private fun mapProductResource(it: ProductDefinition) =
            it.resources.stream().map { ProductResourceDto(it.type, it.quantity) }.toList()


}