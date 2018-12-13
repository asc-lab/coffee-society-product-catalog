package pl.altkom.coffee.productcatalog.api.dto

data class ProductDefinitionDto(
        val id: String,
        val name: String,
        val resources: List<ProductResourceDto>)