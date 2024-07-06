# Java Exercise

## Introduction to the design and implementation

TODO


## Assumptions and trade-offs

1. I assumed that each individual `xml` file contains one trade entry.
2. For simplicity, all fields in `TradeData` are strings.
3. For simplicity, the data is currently statically bundled with the application.


## How to run

The only required dependency to run the solution is **Docker** with **docker-compose**.

1. Start the app:
   Run:
   ```bash
   docker-compose up app
   ```

2. Hit the endpoint by visiting `http://localhost:8080/report`:
   Run:
   ```bash
   curl localhost:8080/report
   ```

**NOTE**: The data is located in `src/main/resources/data`.
If you'd like to run the app with a different set of data, simply replace the contents of that
directory and restart the app.

