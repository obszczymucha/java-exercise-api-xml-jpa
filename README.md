# Java Exercise

## Introduction to the design and implementation

The solution utilizes the latest version of **Spring Boot** and its components (`3.3.1`).  
The latest version of **Spring Boot** requires **Java 17** to run, so that is what the project uses.  
The project is fully *Dockerized*, eliminating the need to install any specific Java version.

The **API** and **JPA** parts are handled using Spring Boot. The **XML** parsing utilizes **Java's  
built-in** parsing features. The **database** is an **in-memory H2**. The data is bundled with the  
application.

I've also utilized the **Lombok** library, which removes a lot of boilerplate code and allows for  
the clean use of the `val` keyword (which promotes immutability).

The design of the solution prioritizes simplicity and **extensibility** (as requested). I've used  
the **Strategy** design pattern to implement each filtering strategy. Each implemented filtering  
strategy is automatically injected using the **Spring DI context**. This approach should facilitate  
adding new filtering logic if the business requires it.

There are three layers in the application:
 * Controller
 * Service
 * Repository

Each layer maintains its own data model, except for the controller layer. The controller layer uses  
the service model as a DTO.

The app parses the files and stores them in the database on startup, then queries the database on  
each API request to the `/report` endpoint.

See the following assumptions and trade-offs section for a justification of the database type,  
bundling the data, and service model reuse in the controller.

I aim for self-documenting code, so I added comments only when absolutely necessary. I hope the  
implementation speaks for itself. If it doesn't, then I have failed miserably.


## Assumptions and trade-offs
1. I assumed that each individual `xml` file contains one trade entry.
2. I assumed that we don't need to worry about case sensitivity for seller/buyer names.
3. For simplicity, all fields in the `TradeData` model and entity are strings. It's natural to  
   replace this with proper types such as `BigDecimal` for monetary amounts in a real-world  
   scenario.
4. For simplicity, the data is currently statically bundled with the application (see how-to-run  
   instructions if you'd like to use a different dataset).
5. For simplicity, the in-memory H2 database was used. For a real-world scenario, a proper  
   standalone database (I'd choose Postgres) is recommended.
6. Usually, the controller layer should maintain its own model (DTO) and translate it between the  
   service layer model, but in this case, I'm just reusing the service model to make the solution  
   simpler.
7. Error handling is not implemented. A proper solution would handle errors gracefully.
8. I didn't test model-to-entity transformations. I would in a real-world system.


## How to run

The only required dependency to run the solution is **Docker** with **docker-compose**.

1. Start the app:  
   Run:
   ```bash
   docker-compose up app
   ```

2. Trigger the endpoint by visiting `http://localhost:8080/report`:  
   Run:
   ```bash
   curl localhost:8080/report
   ```

**NOTE**: The data is located in `src/main/resources/data`.
If you'd like to run the app with a different set of data, simply replace the contents of that  
directory and restart the app.

**NOTE**: The first startup might be slow - this is due to gradle fetching dependencies and building  
the project. Any subsequent runs should be fast.
