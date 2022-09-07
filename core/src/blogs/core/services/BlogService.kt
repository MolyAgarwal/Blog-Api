package blogs.core.services

import blogs.core.models.Blog
import blogs.core.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogService @Inject constructor(
    private val blogRepository: BlogRepository
) {
    fun getAll(): List<Blog> = blogRepository.getAll()
    fun createUser(name: String, email: String): List<User> = blogRepository.createUser(name,email)
    fun createBlog(userId: Long, title: String, content: String): List<Blog> = blogRepository.createBlog(userId,title,content)
    fun updateBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog> = blogRepository.updateBlog(userId,blogId,title,content)
    fun deleteBlog(userId: Long, blogId: Long): List<Blog> = blogRepository.deleteBlog(userId,blogId)
}
