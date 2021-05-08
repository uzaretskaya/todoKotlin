package ru.uzaretskaya.todo.business.search;


class CategorySearchValues(
    val id: Long?,
    val title: String?,
    val email: String?
) {

    override fun toString(): String =
        "CategorySearchValues(id=$id, title=$title, email=$email)"

}
