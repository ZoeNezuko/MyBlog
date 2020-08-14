## Project Introduction

MyBlog is a web Application with separated front and back ends. The project is developed by SpringBoot+Vue


### Technology used in the project

#### Back end

1. Spring Boot
2. Spring data jpa
3. Shior
4. MySQL
5. Lombok

#### Front end

1. Vue
2. ElementUI
3. Axios
4. vue-router
5. Vuex
7. vue-cli4

### Project user interface
 
Home page:

![Home](https://github.com/ZoeNezuko/Image/blob/master/img/homepage.png)


First, after successfully logging in, different users will see different system navs according to their roles. The complete navs is as follows:

![Admin](https://github.com/ZoeNezuko/Image/blob/master/img/AdminCanSee.png)


After the user logs in, they may see differences as follows:

![User](https://github.com/ZoeNezuko/Image/blob/master/img/UserCanSee.png)


Next is the write blog page：

![Blog](https://github.com/ZoeNezuko/Image/blob/master/img/WriteBlogMd.png)


Next is the upload resource page：

![p281](https://github.com/ZoeNezuko/Image/blob/master/img/UploadedResources.png)

## Quick start

1. You need to create an empty database projectdb in the local MySQL in advance, and modify the data configuration in the project (in the application.yml file under the resources directory).

2. Open Project front end Enter the following commands in the command line:

```
# Installation dependencies
npm install

# Start the project at localhost:8080
npm run serve

```
**OK, at this point, the server has started successfully. At this time, directly enter `http://localhost:8080/` in the address bar to access the project.**
