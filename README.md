# InstagramCloneOne
 Here's my attempt for creating an instagram clone api as my project and understanding git and deploying along with it
 
 Right now hard coded the current logged in user in ServiceClass.java and intended to get the current logged in user from ui or some other way while logging in.
 It has 3 tables in the database for users, posts and followers

Below are postman activities on the api

Add user
POST : http://localhost:8080/adduser?username=naruto&description=my bio is my ninja way

All users
GET : http://localhost:8080/users

Get User
GET : http://localhost:8080/username/naruto

Get User
GET : http://localhost:8080/userid/21

Update User
PUT : Work in progress

Add post
POST : http://localhost:8080/addpost?description=post text is fine I guess

Update Post
PUT : http://localhost:8080/updatepost?description=post text is fine I guess&postid=15

Get All Posts
GET : http://localhost:8080/posts

Get Posts of User
GET : http://localhost:8080/posts/venky2222

Get My Posts
GET : http://localhost:8080/myposts

Get a Post
GET : http://localhost:8080/postid/12

Delete a Post (No constrain to only my posts right now. Can delete any post)
DELETE : http://localhost:8080/postid/12

Like or unlike a liked post
PUT : http://localhost:8080/postlikeunlike/14

Follow a User
PUT : http://localhost:8080/follow/naruto

Unfollow a user
PUT :  http://localhost:8080/unfollow/naruto

Get Followers
GET : http://localhost:8080/followers

Get Following
GET : http://localhost:8080/following

