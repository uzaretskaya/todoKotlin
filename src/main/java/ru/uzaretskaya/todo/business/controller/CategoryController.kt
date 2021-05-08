package ru.uzaretskaya.todo.business.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus.NOT_ACCEPTABLE
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.uzaretskaya.todo.business.entity.Category
import ru.uzaretskaya.todo.business.search.CategorySearchValues
import ru.uzaretskaya.todo.business.service.CategoryService
import ru.uzaretskaya.todo.business.util.AllExecutedMethodsLogger.Companion.loggingMethodName
import ru.uzaretskaya.todo.business.util.ResponseEntity
import java.lang.String.format

@RestController
@RequestMapping("/category")
class CategoryController(
    @Autowired
    val categoryService: CategoryService
) {

    @PostMapping("/all")
    fun findAll(@RequestBody email: String): ResponseEntity<List<Category>> {
        loggingMethodName(format("CategoryController: findAll(%s)", email))
        return ResponseEntity.ok(categoryService.findAll(email))
    }

    @PutMapping("/add")
    fun add(@RequestBody category: Category): ResponseEntity<Category> {
        loggingMethodName(format("CategoryController: add(%s)", category))

        if (category.id != null && category.id != 0L) {
            return ResponseEntity("Redundant parameter: ID must be NULL", NOT_ACCEPTABLE)
        }

        if (category.title == null || category.title.isEmpty()) {
            return ResponseEntity("Missing parameter: title", NOT_ACCEPTABLE)
        }

        return ResponseEntity.ok(categoryService.add(category))
    }



    @PatchMapping("/update")
    fun update(@RequestBody category: Category): ResponseEntity<Category> {
        loggingMethodName(format("CategoryController: update(%s)", category))

        if (category.id == null || category.id == 0L) {
            return ResponseEntity("Missing parameter: id", NOT_ACCEPTABLE)
        }

        if (category.title == null || category.title.isEmpty()) {
            return ResponseEntity("Missing parameter: title", NOT_ACCEPTABLE)
        }

        categoryService.update(category)
        return ResponseEntity<Category>(OK)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestBody id: String?): ResponseEntity<Category> {
        loggingMethodName(format("CategoryController: delete(%s)", id))

        val idL: Long? = id?.toLong()
        if (idL == null || idL == 0L) {
            return ResponseEntity("Missing parameter: id", NOT_ACCEPTABLE)
        }

        try {
            categoryService.deleteById(idL)
        } catch (e: EmptyResultDataAccessException) {
            e.printStackTrace()
            return ResponseEntity(format("Category id=%d not found!", idL), NOT_ACCEPTABLE)
        }

        return ResponseEntity<Category>(OK)
    }

    @PostMapping("search")
    fun search(@RequestBody categorySearchValues: CategorySearchValues): ResponseEntity<List<Category>> {
        loggingMethodName(format("CategoryController: search(%s)", categorySearchValues))

        val categories = categoryService.findByValues(categorySearchValues.title, categorySearchValues.email)
        return ResponseEntity.ok(categories)
    }

    @PostMapping("/id")
    fun findById(@RequestBody id: Long?): ResponseEntity<Category> {
        loggingMethodName(format("CategoryController: findById(%d)", id))

        if (id == null || id == 0L) {
            return ResponseEntity("Missing parameter: id", NOT_ACCEPTABLE)
        }

        val category: Category = try {
            categoryService.findById(id)
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
            return ResponseEntity(format("Category id=%d not found!", id), NOT_ACCEPTABLE)
        }
        return ResponseEntity.ok(category)
    }

}