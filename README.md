
<a name="AngelDex"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
   ![logo](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/7775bcef-2503-4ec1-87f0-7668dec30273)
  <h3 align="center">AngelDex-Web-Project</h3>
</div>



<!-- Website view -->
## Website view

<br />
<img src="![signup tab](https://github.com/AngelDoychev/AngelDex-Web-Project/src/main/resources/static/images/logo.png)">
<br />
![login tab](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/b73e98ce-dfdf-4388-b297-bf49caa58799)
<br />
![home tab](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/429dc808-22b6-4dac-b6da-3f34f08ccc5f)
<br />
![home tab 2](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/4e97fb10-6830-4459-a0c3-f5011c8e3787)
<br />
![home tab 3](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/06d6f276-209a-4e09-a6b4-cd179da00fe5)
<br />
![articles](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/b3d6a656-6245-402b-978e-ac9275773b45)
<br />
![unconfirmed article](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/ce30db58-f4b2-44b1-aa52-bf14c44675a9)
<br />
![crypto tab](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/3445015d-c6a7-4310-a481-de0be4b53be6)
<br />
![team tab](https://github.com/AngelDoychev/AngelDex-Web-Project/assets/120913858/1d8bb43a-b831-4d5c-a138-154a4738efb2)
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
- Usres are able to update the prices of cryptocurrencies.
- Admin can update the prices of currencies and "hard update" the top 100 if there are changes in the market.




<!-- CONTACT -->
## Contact

Angel Doychev - Email: angeldoychev285@abv.bg Phone-number: 0882704879

Project Link: [https://github.com/AngelDoychev/AngelDex-Web-Project](https://github.com/AngelDoychev/AngelDex-Web-Project)


