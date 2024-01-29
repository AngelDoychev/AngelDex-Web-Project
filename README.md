
<a name="AngelDex"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
<img src="https://i.postimg.cc/90B4KcNK/logo.png" alt="logo">
  <h3 align="center">AngelDex-Web-Project</h3>
</div>



<!-- Website view -->
## Website view

<br />
<img src="https://i.postimg.cc/d1D2cZKd/signup-tab.png" alt="signup">
<br />
<img src="https://i.postimg.cc/d1D2cZKd/signup-tab.png" alt="login">
<br />
<img src="https://i.postimg.cc/Hx4921Hv/home-tab.png" alt="home">
<br />
<img src="https://i.postimg.cc/sxLQPTB0/home-tab-2.png" alt="home2">
<br />
<img src="https://i.postimg.cc/kgsVfMtb/home-tab-3.png" alt="home3">
<br />
<img src="https://i.postimg.cc/LXL2Qv0b/articles.png" alt="articles">
<br />
<img src="https://i.postimg.cc/26wX5Zq5/unconfirmed-article.png" alt="unconfirmed_article">
<br />
<img src="https://i.postimg.cc/zGZw9fB2/crypto-tab.png" alt="crypto">
<br />
<img src="https://i.postimg.cc/506nfRMs/team-tab.png" alt="team">
<br />




<!-- ABOUT THE PROJECT -->
## About The Project


This is a Web project made from Angel Doychev in order to  observe the rapidly changing prices of crypto currencies. 
Other functionalities include creating and observing news articles about crypto currencies. There is an
account system that lets people create and login into their account in order to track who uploaded which article.
The project is build in a Spring Model View Controller architecture type.




### Built With

This section lists the major dependecies used in the project:

* Spring boot
* Spring Data JPA
* Spring Security
* Spring Oauth2
* Thyemeleaf
* Coin Market Cap API
* Spring boot stater web
* Sprin boot validations
* Mysql connector J
* Javax Mail
* Bootstrap
* Jquery




<!-- Entities -->
## Entities

The entities were instantiated using jakarta.persistance JPQL with the @Entity annotation.

- BaseEntity (used to hold the id of all other entities)
- Article 
- Currency
- RegisteredEmail
- UserEntity
- Role




<!-- Relations -->
## Relations

- BaseEntity (is extended by every other entity)
- Article (ManyToOne relation with UserEntity)
- Currency (Unidirectional relation from UserEntity)
- RegisteredEmail (Stand alone entity)
- UserEntity (ManyToMany relation with Role,
             (OneToMany relation with Currency,
             (OneToMany relation with Article)
- Role (ManyToMany relation with UserEntity)


<!-- Dto -->
## Dto's

Data transfer objects were created so we don't expose the objects.
Validations were applied on the Dtos varriables. in order to validate size, notnull, etc...

- ArticleBindingDto
- ChangePasswordDto
- CurrencyDto
- EmailRegisterDto
- ForgotPasswordDto
- LoginServiceModel
- RegisterUserBindingDto
- SearchArticlesDto


<!-- MVC architecture -->
## MVC Architecture 

The MVC architecture type was used in order to structure the application in a way that 
makes it easier to digest. The program is structured as follows.

- config (all app configurations are stored here)
- model (entities, Dtos and Enumerations are stored here)
- repository (For every entity a repositories was created which uses  springframworks JpaRepository in order to generate functions from pre-done JPQL)
- security (A CurrentUser class was created that lives in the @Sessionscope and stores the state of the user and if the user is anonymous or logged in)
- service (All of the logic is stored in the services in order to create a Facade design pattern)
- util (Utilizations are stored and implemented here)
- web (All of the controllers which link the back end to the frontend are stored here)



<!-- Oauth2 -->
## Oauth2

Oauth2 was used and implemented in the SecurityConfig file with default
success url and the ability to log in using the /login link.
Links used:
- https://developers.facebook.com
- https://console.cloud.google.com
- https://developer.linkedin.com
- https://github.com/settings/developers



<!-- Coin Market Cap API -->
## Coin Market Cap API

The Coin Market Cap API was instantiated in the CMCAPIConfiguration file
and was used in order to pull a JSON file with meta information for
the top 100 cryptocurrencies and upload it into the databse using the 
Currency Entity. Later it is displayed in the CryptoCotroller class.



<!-- Thymeleaf -->
## Thymmeleaf
Thymeleaf was used to make the connection between the back-end and front-end 
and create a seamless work flow between them. 



<!-- ROADMAP -->
## Roadmap

- Home (this is the homepage of the app which grants you access to the most crucial information)
- Articles (this is where news articles are stored, viewed and uploaded)
- Crypto (this is where crypto currencies can be observed)
- Team (this section contains information behind the team that build this app "aka myself")
- Login/Logout (this section lets you traverse into and out of registered accounts)




<!-- USAGE EXAMPLES -->
## Usage

- Users can be registered and login into their accounts.
- Users can log into the platform using Facebook, Github, Google & LinkedIn
- Users who forgot their password can click on the forgot password field
which will redirect them to a page letting them change their password via
an unique code they receive in their email used to registered the account.
- Logged users can view uploaded articles and cryptocurrency prices
- Users who are not logged in only have access to the home page and team section.
- You can also register only your email to receive news via email on the latest movements
in the crypto market. You can do so from any page except the login page.
- There are two types of users: USER & ADMIN.
- Every user can upload articles using the create article form that takes a title, content and URL link thumbnail picture.
- When created articles need to be confirmed before being able to be seen by normal USERS.
- Admins can confirm articles and delete them if they see reason to do so.
- You can read the whole article by clicking on the read more button in the preview article box
- which redirects you to a page with the thumbnail, title and content of the article.
- Also you can see which user wrote the article.
- You can also click on the email of the user who wrote the article to check out more
articles written by this user.
- You can also use the search function to search articles that include the specified word/phrase
in the existing articles.
- Usres are able to update the prices of cryptocurrencies.
- Admin can update the prices of currencies and "hard update" the top 100 if there are changes in the market.




<!-- CONTACT -->
## Contact

Angel Doychev - Email: angeldoychev285@abv.bg Phone-number: 0882704879

Project Link: [https://github.com/AngelDoychev/AngelDex-Web-Project](https://github.com/AngelDoychev/AngelDex-Web-Project)


