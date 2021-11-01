# Tour of heroes

In this basic API, we manage the content of some heroes, the structure of the project are:

## 1. Controllers
With this, we handle the HTTP request, the existing controllers are:

* Heroes Controller
* Power Controller
* Authentication Controller

    In this controller we manage the authentication process, if the request has good credentials, we create and send a JWT, otherwise we throw an exception
    
## 2. Services 
In this module, we manage all the logic, the services available so far are:
* Hero Service
* Power Service
* AuthUser Service: Inside this service, we implements *UserDetailService* to manage the way that we're retrieving the users, with this method, spring knows if a user credentials are valid or not

## 3. Repositories
Until now, there are basic repositories that implements JpaRepository that bring us a lot of pre-bult methods to connect with the DB

## 4.Entities 
Using JPA, we have the ORM, this Entities are the mappers to the Database tables 