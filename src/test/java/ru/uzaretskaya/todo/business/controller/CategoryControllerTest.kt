package ru.uzaretskaya.todo.business.controller

import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import ru.uzaretskaya.todo.auth.entity.User
import ru.uzaretskaya.todo.business.entity.Category
import ru.uzaretskaya.todo.business.service.CategoryService

@RunWith(MockitoJUnitRunner::class)
class CategoryControllerTest {

    @InjectMocks
    lateinit var categoryController: CategoryController

    @Mock
    lateinit var categoryService: CategoryService

    lateinit var user1: User
    lateinit var user2: User

    @Before
    fun init() {
        user1 = User("test@email.com", "", "User1", null)
        user2 = User("test2@email.com", "", "User2", null)
    }

    @Test
    fun `should return all categories for correct user email`() {
        /*val category1 = category("Category1", user1)
        val category2 = category("Category2", user1)
        val category3 = category("Category3", user2)

        val result = categoryController.findAll(user1.email)
        assertEquals(result, listOf(category1, category2))*/
    }

    private fun category(name: String, testUser: User) =
        Category(name, 0, 0, testUser)
}