package pl.altkom.coffee.productcatalog.domain.model

import pl.altkom.coffee.productcatalog.api.enums.ProductResourceType

class ProductResource(val type: ProductResourceType,
                      val quantity: Int)