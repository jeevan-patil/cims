# Car Inventory Management &amp; Sales

Use following commands to run the application on the command line:
1. To build the application -> mvn clean package
2. To run the tests -> mvn test
3. To run the application -> mvn spring-boot:run

These are the CURL for some of the APIs.

1. API to create a car

`curl --location --request POST 'http://localhost:8080/cims/admin/add-car' \
--header 'Content-Type: application/json' \
--data-raw '{
"make" : "maruti",
"year" : "3433",
"price" : 343.234
}'`

2. API to list cars

`curl --location --request GET 'http://localhost:8080/cims/car/list'`

3. API to remove cars from inventory

`curl --location --request POST 'http://localhost:8080/cims/admin/remove' \
--header 'Content-Type: application/json' \
--data-raw '["27f8af20-a122-4a77-afb4-f533e5692df4"]'`

4. API to buy a car

`curl --location --request POST 'http://localhost:8080/cims/car/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": "aa43eded-07c6-4bf1-bd16-495c7fa0008b",
"buyingPrice" : 500
}'`