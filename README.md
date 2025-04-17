# ShopNow Order Service

This is the Order Service for the ShopNow e-commerce platform. It handles order creation, management, and processing.

## Environment Configuration

This application uses environment variables for configuration to enhance security by keeping sensitive information out of the codebase.

### Setting Up Environment Variables

1. Copy the `.env.sample` file to a new file named `.env`:
   ```
   cp .env.sample .env
   ```

2. Edit the `.env` file and fill in the values for all environment variables:
   - Database configuration:
     - `SPRING_DATASOURCE_URL`: JDBC URL for the PostgreSQL database
     - `SPRING_DATASOURCE_USERNAME`: Database username
     - `SPRING_DATASOURCE_PASSWORD`: Database password (keep this secure)
   
   - JWT configuration:
     - `JWT_SECRET`: A secure random string used for signing JWT tokens (at least 32 characters)
     - `JWT_EXPIRATION`: Token expiration time in milliseconds (default: 86400000 = 24 hours)
   
   - Server configuration:
     - `SERVER_PORT`: The port on which the service will run

3. For production environments, set these variables in your deployment platform (e.g., Kubernetes secrets, Docker environment variables, etc.) rather than using a .env file.

### Generating a Secure JWT Secret

For the JWT secret, use a secure random generator. Here's an example using OpenSSL:

```
openssl rand -base64 32
```

## Running the Application

Make sure your environment variables are set before running the application.

### Using Maven

```
mvn spring-boot:run
```

### Using Java

```
java -jar target/order_service-0.0.1-SNAPSHOT.jar
```

## Security Considerations

- Never commit the `.env` file or any file containing actual secrets to version control
- Rotate secrets regularly, especially in production environments
- Use a secrets management solution for production deployments