package ru.uzaretskaya.todo.business.service

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

    fun add(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun update(category: Category) {
        categoryRepository.save(category)
    }

    fun deleteById(id: Long) {
        categoryRepository.deleteById(id)
    }

    fun findByValues(title: String?, email: String?): List<Category> {
        return categoryRepository.find(title, email)
    }

    fun findById(id: Long): Category {
        return categoryRepository.findById(id).get()
    }
}