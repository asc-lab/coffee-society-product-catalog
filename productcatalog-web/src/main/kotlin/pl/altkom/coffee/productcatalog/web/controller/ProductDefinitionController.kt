package pl.altkom.coffee.productcatalog.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pl.altkom.coffee.productcatalog.api.dto.ProductDefinitionDto
import pl.altkom.coffee.productcatalog.domain.query.ProductDefinitionQuery
import pl.altkom.coffee.productcatalog.domain.service.ProductDefinitionService

@RestController
@RequestMapping("/api")
class ProductDefinitionController(
        private val productDefinitionService: ProductDefinitionService,
        private val productDefinitionQuery: ProductDefinitionQuery) {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/definitions")
    fun getActiveProductVersion(): ResponseEntity<List<ProductDefinitionDto>> {
        return ResponseEntity.ok().body(productDefinitionQuery.findAllActiveProduct())
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addNewProduct")
    fun defineNewProduct(@RequestBody dto: ProductDefinitionDto): ResponseEntity<Void> {
        productDefinitionService.defineNewProduct(dto)
        return ResponseEntity.ok().build()
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addNewProductVersion")
    fun defineNewVersionProduct(@RequestBody dto: ProductDefinitionDto): ResponseEntity<Void> {
        productDefinitionService.defineNewProductVersion(dto)
        return ResponseEntity.ok().build()
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/deactivateProduct/{id}")
    fun deactivateProduct(@PathVariable("id") id: String): ResponseEntity<Void> {
        productDefinitionService.deactivateProduct(id)
        return ResponseEntity.ok().build()
    }
}