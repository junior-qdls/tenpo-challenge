# tenpo-challenge
In order to deploy the App, we need to do next steps first:

## Download this repo and go to the root directy
```
cd tenpo-challenge
```

## Generate the jar 
```
mvn clean package
```

## Build the dockerfile
```
sudo docker build -t juniorqdls/tests .
```

## Run through docker compose

```
sudo docker compose up -d .
```

# How to test this App?
Use the postman collection included in the source code
tenpo.postman_collection.json

First Use the sign up sample request in order to create a user

Then Log in into the app, using the username and password , you've passed in the sign up

Once you've been login you can play around the following apis:

challenge-add : Add numbers and get the result as response
audit-history: Get the history of tracks actions an user does to every Api , query params page and size
logout: Log out from the app.

To get Access the swagger file go to:

127.0.0.1:8080/swagger-ui





