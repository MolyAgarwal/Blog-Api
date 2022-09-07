package db.blogs

import blogs.core.models.Blog
import blogs.core.models.User
import blogs.core.services.BlogRepository
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class BlogRepositoryImpl(
    @Inject private val dataSource: DataSource
) : BlogRepository {
    override fun getAll(): List<Blog> {
        val query = dataSource.connection.prepareStatement("SELECT * FROM blogs")
        val result = query.executeQuery()
        val blogs = mutableListOf<Blog>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("title") as String
            val content = result.getString("content") as String

            blogs.add(Blog(id, name, content))
        }
        return blogs
    }

    override fun createUser(name: String, email: String): List<User> {
        val insertquery = dataSource.connection.prepareStatement("INSERT into users(name,email) VALUES (?,?)")
        insertquery.setString(1,name)
        insertquery.setString(2,email)
        insertquery.executeUpdate()

        val insertedUser = dataSource.connection.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 1")
        val result = insertedUser.executeQuery()
        val user = mutableListOf<User>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name= result.getString("name") as String
            val email = result.getString("email") as String
            user.add(User(id,name,email))
             }
        return user
    }

    override fun createBlog(userId: Long, title: String, content: String): List<Blog> {
        val insertquery = dataSource.connection.prepareStatement("INSERT into blogs(user_id,title,content,published_at,created_at) VALUES (?,?,?,?,?)")
        insertquery.setLong(1,userId)
        insertquery.setString(2,title)
        insertquery.setString(3,content)
        insertquery.setTimestamp(4,Timestamp(System.currentTimeMillis()))
        insertquery.setTimestamp(5,Timestamp(System.currentTimeMillis()))


        insertquery.executeUpdate()

        val insertedBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs ORDER BY id DESC LIMIT 1")
        val result = insertedBlog.executeQuery()
        val blog = mutableListOf<Blog>()

        while (result.next()) {
//            val user_id =result.getInt("user_id").toLong()
            val id = result.getInt("id").toLong()
            val title= result.getString("title") as String
            val content = result.getString("content") as String
            blog.add(Blog(id,title,content))
        }
        return blog
    }

    override fun updateBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog> {
        val updateQuery=dataSource.connection.prepareStatement("UPDATE blogs SET title=?,content=?,updated_at=? where id=${blogId} and user_id=${userId}")
        updateQuery.setString(1,title)
        updateQuery.setString(2,content)
        updateQuery.setTimestamp(3,Timestamp(System.currentTimeMillis()))
        updateQuery.executeUpdate()

        val updatedBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs where id=${blogId} and user_id=${userId}")
        val result = updatedBlog.executeQuery()
        val blog = mutableListOf<Blog>()

        while (result.next()) {
//            val user_id =result.getInt("user_id").toLong()
            val id = result.getInt("id").toLong()
            val title= result.getString("title") as String
            val content = result.getString("content") as String
            blog.add(Blog(id,title,content))
        }
        return blog
    }

    override fun deleteBlog(userId: Long, blogId: Long): List<Blog> {

        val deleteBlog = dataSource.connection.prepareStatement("SELECT * FROM blogs where id=${blogId} and user_id=${userId}")
        val result = deleteBlog.executeQuery()
        val blog = mutableListOf<Blog>()

        while (result.next()) {
//            val user_id =result.getInt("user_id").toLong()
            val id = result.getInt("id").toLong()
            val title= result.getString("title") as String
            val content = result.getString("content") as String
            blog.add(Blog(id,title,content))
        }

        val deleteQuery=dataSource.connection.prepareStatement("DELETE FROM blogs where id=? and user_id=?")
        deleteQuery.setLong(1,blogId)
        deleteQuery.setLong(2,userId)
        deleteQuery.executeUpdate()
        return blog
    }

}
