package pl.altkom.coffee.productcatalog.domain.model

import org.springframework.data.annotation.Id

class ProductDefinition {
    @Id
    var id: String? = null
    val name: String
    val resources: List<ProductResource>
    val version: Long
    var active: Boolean
        private set


    constructor(name: String, resources: List<ProductResource>, version: Long) {
        this.name = name
        this.resources = resources
        this.version = version
        this.active = true
    }

    fun deactivate() {
        this.active = false
    }

    companion object {
        fun createNewProduct(name: String,
                             resources: List<ProductResource>): ProductDefinition {
            return ProductDefinition(name, resources, 0)
        }

        fun createNewProductVersion(formerVersion: ProductDefinition, resources: List<ProductResource>): ProductDefinition {
            return ProductDefinition(formerVersion.name, resources, formerVersion.version.inc())
        }

    }

}