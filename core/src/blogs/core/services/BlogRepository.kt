package blogs.core.services

import blogs.core.models.Blog
import blogs.core.models.User

interface BlogRepository {
    fun getAll(): List<Blog>
    fun createUser(name: String, email: String): List<User>
    fun createBlog(userId: Long, title: String, content: String): List<Blog>
    fun updateBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog>
    fun deleteBlog(userId: Long,blogId: Long): List<Blog>
}
