package blogs

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/api/v1/blogs")
class BlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Get("/")
    fun getAll(): HttpResponse<Any> {
        val blogs = blogController.getAll()
        return blogs.getHttpResponse()
    }
}

@Controller("/api/v1/users")
class createUserApi @Inject constructor(
    private val blogController: BlogController
){
    @Post("/")
    fun createUser(name:String,email:String):HttpResponse<Any>{
        val blog = blogController.createUser(name,email)
        return blog.getHttpResponse()
//        return userId.toString() +  " " + title + " " + content
    }

}

@Controller("/api/v1/blogs")
class createBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Post("/")
    fun createBlog(userId:Long,title:String,content:String):HttpResponse<Any>{
        val blog = blogController.createBlog(userId,title,content)
        return blog.getHttpResponse()
    }

}
@Controller("/api/v1/blogs")
class updateBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Put("/")
    fun updateBlog(userId: Long, blogId: Long, title: String, content: String): HttpResponse<Any> {
        val blog = blogController.updateBlog(userId,blogId,title,content)
        return blog.getHttpResponse()
    }
}


@Controller("/api/v1/blogs")
class deleteBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Delete("/")
    fun deleteBlog(userId: Long, blogId: Long): HttpResponse<Any> {
        val blog = blogController.deleteBlog(userId,blogId)
        return blog.getHttpResponse()
    }
}
