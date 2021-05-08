package ru.uzaretskaya.todo.business

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.uzaretskaya.todo.business.entity.Category
import ru.uzaretskaya.todo.business.repository.CategoryRepository

@Service
class CategoryService(
    @Autowired
    val categoryRepository: CategoryRepository
) {

    fun findAll(email: String): List<Category> =
        categoryRepository.findByUserEmailOrderByTitleAsc(email)

}