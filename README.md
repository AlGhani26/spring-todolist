# Spring TodoList API

A secure RESTful API for managing todo checklists built with Spring Boot, JWT authentication, and MySQL.

## Features
- User authentication & authorization with JWT
- Create, read, update, delete checklists
- Manage checklist items
- Secure endpoints with role-based access
- MySQL database with JPA

## Tech Stack
- **Backend**: Spring Boot 3.x
- **Security**: Spring Security + JWT
- **Database**: MySQL
- **Build Tool**: Maven
- **Testing**: JUnit 5

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `POST /api/auth/validate` - Validate token

### Checklists
- `GET /api/checklists` - Get all checklists for logged-in user
- `GET /api/checklists/{id}` - Get specific checklist (must be owned by user)
- `POST /api/checklists` - Create new checklist
- `DELETE /api/checklists/{id}` - Delete checklist

### Items
- `GET /api/checklists/{checklistId}/items` - Get items in checklist
- `POST /api/checklists/{checklistId}/items` - Add item to checklist
- `PUT /api/checklists/{checklistId}/items/{itemId}` - Update item
- `DELETE /api/checklists/{checklistId}/items/{itemId}` - Delete item

## Authentication
Include JWT token in Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## Common Issues & Solutions

### 403 Forbidden on GET /api/checklists/{id}
This occurs when:
1. The checklist ID doesn't exist
2. The checklist is owned by another user
3. JWT token is invalid/expired

**Solution:**
- Verify the checklist ID exists in your account
- Ensure JWT token is valid (check with `/api/auth/validate`)
- Use checklist IDs returned from `GET /api/checklists`

## Setup Instructions
1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run `mvn clean install`
4. Start application with `mvn spring-boot:run`

## Database Schema
- **users**: User accounts
- **checklists**: Todo checklists (linked to users)
- **items**: Individual checklist items (linked to checklists)

## Testing
Use Postman or similar tool with the provided endpoints. Always include valid JWT token in headers for protected endpoints.
