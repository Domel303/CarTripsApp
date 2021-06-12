# CarTripsApp

Simply application for creating and managing car trips with other users. App provides lists of created trips, users and their cars.

# Technology
Project is created with:

##### BackEnd
* Java Spring boot
* MySQL for development PostgreSQL in production
* Gradle
* Spring security
##### FrontEnd
* ReactJS
* Rect Bootstrap


# Structure
* Backend in folder src/java
* Frontend in folder src/web

# Admin

In this application in production database is as admin set user with credentials:
* Username: Dominik

* Password: 123456789

Administrator has priviliges to delete and update records

# Database
![Database schema](/Schema.jpg)


# Run
To run this app database settings is needed.
Change  [application.properties](https://github.com/Domel303/CarTripsApp/blob/master/src/main/resources/application.properties) to acces your database. Also 'ROLE_USER' and 'ROLE_ADMIN' is needed in table roles.

To run the application simply run
```
gradle bootRun
```
Be careful ! some env properties may be needed like in  [application.properties](https://github.com/Domel303/CarTripsApp/blob/master/src/main/resources/application.properties)

All dependencies should be downloaded automatically via gradle.

# App deploy
App is available on the [heroku](https://car-trips.herokuapp.com/)


