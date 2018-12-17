package pl.altkom.coffee.productcatalog.domain.model

import org.springframework.data.annotation.Id
import java.math.BigDecimal

class ProductDefinition(
        val name: String,
        val resources: List<ProductResource>,
        val version: Long,
        val tax: BigDecimal
) {
    @Id
    var id: String? = null
    var active: Boolean
        private set


    init {
        this.active = true
    }

    fun deactivate() {
        this.active = false
    }

    companion object {
        fun createNewProduct(
                name: String,
                resources: List<ProductResource>,
                tax: BigDecimal
        ): ProductDefinition {
            return ProductDefinition(name, resources, 0, tax)
        }

        fun createNewProductVersion(formerVersion: ProductDefinition, resources: List<ProductResource>, tax: BigDecimal): ProductDefinition {
            return ProductDefinition(formerVersion.name, resources, formerVersion.version.inc(), tax)
        }
    }
}