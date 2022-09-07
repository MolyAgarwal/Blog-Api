package blogs

import blogs.core.services.BlogService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {
    override fun getAll(): Response<Any> {
        val response = blogService.getAll()
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun createUser(name: String, email: String): Response<Any> {
        val response = blogService.createUser(name,email)
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun createBlog(userId: Long, title: String, content: String): Response<Any> {
        val response = blogService.createBlog(userId,title,content)
        return Response(ResponseType.SUCCESS, body = response)

    }

    override fun updateBlog(userId: Long, blogId: Long, title: String, content: String): Response<Any> {
        val response = blogService.updateBlog(userId,blogId,title,content)
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun deleteBlog(userId: Long, blogId: Long): Response<Any> {
        val response = blogService.deleteBlog(userId,blogId)
        return Response(ResponseType.SUCCESS, body = response)
    }
}
