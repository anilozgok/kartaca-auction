## Kartaca Intership Task

### Definition

Goal of the project is to create an auction web project which users can log in and
offer bids to desired items. NOTE: This task only includes back end of the web project. 
It does not include front end therefore you need to use Postman to test the systems.

Back end developed using MVC architecture, Java, Spring Boot, PostgreSQL for out database and 
Redis to store our session information. The reason behind using Redis is to fast access to users session information. 
Also, we can access pgadmin to connect database gui.


### Requirements
* Docker: for installation
* Java 17+: for development
* Maven: for development
* Postman: for testing


### Installation

You can directly run the project using docker-compose

```shell
docker compose up
```



### Using project

To register:
```http request
http://localhost:8080/api/auth/register

Sample Request Body:

{
    "userInfo": {
        "firstName": "Can",
        "lastName": "Özgök",
        "userName": "canozgok",
        "password": "anilcan123",
        "gender": "MALE"
    }
}

NOTE: You need to authorize requests with the jwt (bearer) token that returns as a response.
```

To log in:
```http request
http://localhost:8080/api/auth/authenticate

Sample Request Body:
{
    "loginInfo": {
        "userName": "canozgok",
        "password": "anilcan123"
    }
}

NOTE: You need to authorize requests with the jwt (bearer) token that returns as a response.
```

Also, you can find HTTP requests on postman collection file.

We are using web socket to live follow of the auction without refreshing page because in websocket once you obtain the connection 
between client and server it stays open until one of the sides disconnected.
Therefore, to make bid and get bid details we need to send messages to our websocket.
Before that we need to make a little settings on Postman websocket request.

>Step 1: We need to create new websocket request and change websocket type from Raw to Socket.IO and 
> give out websocket endpoint which is ```ws://localhost:9090```
> 
> ![socket-settings-1.png](target%2Fimage-resources%2Fsocket-settings-1.png)

> Step 2: We need to set client version to v2. Also, you can arrange some other settings to such as 
> handshake request timeout, reconnection attempts and reconnection intervals.
> 
> ![socket-settings-2.png](target%2Fimage-resources%2Fsocket-settings-2.png)


> Step 3: After setting our websocket type, giving endpoint and setting client verison. We need to listen our event
> which we will define under the Events section. We need to specify event name which is ```getBidDetails``` and activate the listen the event.
> 
> ![socket-settings-2.png](target%2Fimage-resources%2Fsocket-settings-3.png)

> Step 4: Now you can successfully connect to websocket.
> 
> ![socket-settings-4.png](target%2Fimage-resources%2Fsocket-settings-4.png)

> Step 5: To make a bid on websocket you can create new websocket request and apply first 4 steps. 
> After that to make a bid we need to send a message to websocket. 
> So in the message section we send our bid offer details. Sample message body shown in the image below.
> Don't forget to select message type as JSON, and specify the event name that offers bid which is ``makeBid``
> 
![socket-settings-5.png](target%2Fimage-resources%2Fsocket-settings-5.png)

> Step 6: Now you can send your bid offer and our listener event ``getBidDetails`` will know that you made a bid offer 
> and it will get your bid details without need for refreshing the page.  
> 
![socket-settings-6.png](target%2Fimage-resources%2Fsocket-settings-6.png)


To access pgadmin panel

```http request
http://localhost:5050/

username=test@test.com
password=test
```