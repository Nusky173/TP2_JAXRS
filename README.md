# How to Run

1. Run  `sh run-hsqldb-server.sh` 
2. Run `sh show-hsqldb.sh`

Now your local database is running.

3. Run the rest application first 
4. Then you may run the MainJpa to persist some data in database

# How To Test

## Using Swagger 

Swagger is enable in this application so you can test some Rest API function 
at this adress : `http://localhost:8081/users` for example, it will return all 
the users register of the application

## Using Rest Client

Or you can test using whatever Rest Client you want.

We personally used `chrome-extension://ehafadccdcdedbhcbddihehiodgcddpl/index.html`

We write some example of command below.

# Development 

 ## Users

Our application is able to create two types of users : `Professional` or `Individual`.

A Professional is linked to an `Agenda` and he can diffuse te url of it with this request :
`localhost:8081/professional/{id}`.
The `Individual` can access to it if he knows the login.

The `Individual` is the user that would be able to book a meet but the `Professional` can not do it.

We can get all the user or just one and also delete user.

/!\ You need to create the `Agenda` of the `Professional` before create it.  

Users are also able to login to the app with a password and a login.

We used a LoginModel to do it so the user will have to request this and write his identifiers in a Json.
`localhost:8081/users/log`

## Agenda 

An `Agenda` is permanently created with a `Profesionnal` and linked to it.
We supposed as they are created by an admin and he can do it using this command: 

`localhost:8081/agenda` and fill some the corresponding information.

An `Agenda` is a List of `Meeting` taken by other user to meet
the `Profesionnal`.

The users can access to the list with the url of the `Agenda` he also needs
to log in to it with a password and a login.

## Meeting

A `Meeting` is an object created by an `Individual` focused on a Date and a title.

For example to take a meeting with a professional the user use this request :

`localhost:8081/agenda/{idAgenda}/book?idUser=idUser`

then he has to fill in the information of the Meeting, 
the date and a Title. 

