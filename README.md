# CustomerReviews
Construction of the MySQL part of persistence layer for a REST API that supports customer reviews and comments for products on an e-commerce application - part of the Udacity Java Development course

## Installation
Download the project via github and open it using an IDE such as IntelliJ

## Usage
Run the project using Maven and use a tool like Postman or the localhost on a web browser to compute the following commands:

## Products
Create product (POST) -> http://localhost:8080/products/
```json
{
	"productName": "Thermos"
}
```

Find product by id (GET) -> http://localhost:8080/products/{productId}

Retrieve list of products (GET) -> http://localhost:8080/products/

## Reviews
Create review (POST) -> http://localhost:8080/reviews/products/{productId}
```json
{
	"rate": 5
}
```

Retrieve list of reviews (GET) -> https://localhost:8080/reviews/products/{productId}

## Comments
Create comment (POST) -> http://localhost:8080/comments/reviews/{reviewId}
```json
{
	"commentContent": "Thermos always keeps my lunch warm throughout the day!"
}
```

Retrieve list of comments (GET) -> http://localhost:8080/comments/reviews/{reviewId}
