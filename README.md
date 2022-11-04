# the Millennium Falcon onboard computer

The project was build using [spring boot framework](https://spring.io/projects/spring-boot).  
The project uses maven and java 19  
There is only one endpoint of type post allowing to obtain the chances of survival of the millennium falcon.  

**The project is accessible at this url: https://millennium-falcon.raspberry78.me/**

## Configuration
The project needs a [millennium-falcon.json file](src/main/resources/millennium-falcon.json) containing all the configuration about the Millennium Falcon.  

<u>Example:</u>
```json
{
  "autonomy": 6,
  "departure": "Tatooine",
  "arrival": "Endor",
  "routes_db": "universe.db"
}
```

The [`routes_db`](src/main/resources/universe.db) property should redirect to a SQLite database file containing the routes from the same directory

## Run the project

To run the project locally, use the command below:
```shell
mvn spring-boot:run
```

## Test the project

To run the tests, use the command below:
```shell
mvn test
```

## CI/CD

[Github Actions](https://github.com/features/actions) tool is used to process the continuous integration and continuous delivery.  
It consists of three steps:
1. Build the application
2. Rune tests
3. Deploy the application
