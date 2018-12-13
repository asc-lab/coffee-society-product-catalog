package pl.altkom.coffee.productcatalog.domain.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pl.altkom.coffee.productcatalog.api.dto.ProductDefinitionDto
import pl.altkom.coffee.productcatalog.domain.model.ProductDefinition
import pl.altkom.coffee.productcatalog.domain.model.ProductResource
import pl.altkom.coffee.productcatalog.domain.repository.ProductDefinitionRepository
import kotlin.streams.toList

@Service
@Transactional
class ProductDefinitionService(
        private val productRepository: ProductDefinitionRepository) {

    fun defineNewProduct(dto: ProductDefinitionDto) {
        productRepository.save(ProductDefinition.createNewProduct(dto.name, mapProductDefinition(dto)))
    }

    fun defineNewProductVersion(dto: ProductDefinitionDto) {
        productRepository.findById(dto.id).ifPresent {
            val productDefinition = ProductDefinition.createNewProductVersion(it, mapProductDefinition(dto))
            it.deactivate()

            productRepository.save(productDefinition)
            productRepository.save(it)
        }
    }

    fun deactivateProduct(id: String) {
        productRepository.findById(id).ifPresent {
            it.deactivate()
            productRepository.save(it)
        }
    }

    private fun mapProductDefinition(dto: ProductDefinitionDto) =
            dto.resources.stream().map { ProductResource(it.type, it.quantity) }.toList()
}
