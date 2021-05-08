package ru.uzaretskaya.todo.business.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.uzaretskaya.todo.business.CategoryService
import ru.uzaretskaya.todo.business.entity.Category

@RestController
@RequestMapping("/category")
class CategoryController(
    @Autowired
    val categoryService: CategoryService
) {

    @GetMapping("/all")
    fun findAll(): List<Category> {
        return categoryService.findAll("test@email.com")
    }

}