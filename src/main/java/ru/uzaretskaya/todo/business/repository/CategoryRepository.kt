package ru.uzaretskaya.todo.business.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.uzaretskaya.todo.business.entity.Category

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {

    fun findByUserEmailOrderByTitleAsc(email:String): List<Category>

    @Query(
        "SELECT c from Category c where " +
                "(:title is null or :title='' " +
                "or lower(c.title) like lower(concat('%', :title, '%') ) ) " +
                "and c.user.email=:email " +
                "order by c.title asc"
    )
    fun find(@Param("title") title: String?, @Param("email") email: String?): List<Category>
}