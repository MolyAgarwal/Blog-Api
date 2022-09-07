package blogs

import utility.Response

interface BlogController {
    fun getAll(): Response<Any>
    fun createUser(name: String, email: String): Response<Any>
    fun createBlog(userId: Long, title: String, content: String):  Response<Any>
    fun updateBlog(userId: Long, blogId: Long, title: String, content: String):  Response<Any>
    fun deleteBlog(userId: Long, blogId: Long): Response<Any>
}
