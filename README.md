# 🛍️ CapstoneOne - Amazon Clone (Tuwaiq Bootcamp Project)

This is a capstone project developed during the [Tuwaiq Bootcamp](https://tuwaiq.edu.sa/) that aims to simulate a simplified version of Amazon using **Java Spring Boot**. The project focuses on core backend functionalities including user roles, product management, preference-based recommendations, and stock handling.

---

## 📦 Features

- 🧑‍💼 User registration with roles: `Customer` and `Interester`
- 🛒 Product listing with category preferences
- 🎯 Personalized product suggestions based on user preferences
- 🔄 Balance management and purchasing logic
- 📦 Bulk order support with conditions
- 📊 Admin & merchant product/stock management

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot
- **Database:** H2 (or your preferred DBMS)
- **Build Tool:** Maven
- **Testing:** JUnit (optional)

---

## 🧑‍💻 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/HassanAL-Hussaini/CapstoneOne-Amazon-Tuwaiq-BootCamp.git
cd CapstoneOne-Amazon-Tuwaiq-BootCamp
```

### 2. Run the app

Make sure you have Java 17+ and Maven installed, then:

```bash
./mvnw spring-boot:run
```

### 3. Access H2 Console (if enabled)

- Navigate to: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username/password: (as configured in `application.properties`)

---

## 📁 Project Structure

```
src/
├── controller
├── model
├── service
├── repository
└── dto (if applicable)
```

---

## 🔍 Example API Endpoints

| Method | Endpoint                                      | Description                         |
|--------|-----------------------------------------------|-------------------------------------|
| POST   | `/api/users/register`                         | Register a new user                 |
| GET    | `/api/products`                               | List all products                   |
| PUT    | `/buy-product/{userId}/{merchantId}/{productId}` | User purchases product              |
| GET    | `/user/preference/{userId}`                   | Get personalized recommendations    |

---

## 🚀 Future Improvements

- JWT-based Authentication
- Swagger API documentation
- Frontend integration (React or Angular)
- Dockerization

---

## 🧠 Unique Enhancements in This Capstone Project

This project is not just a basic Amazon clone — it includes creative extensions and smart features that make it stand out:

### 🧩 1. Interester Role & Preferences-Based Filtering

- Users can register as **Interester**, a special role where they define preferences (e.g., Electronics).
- A GET endpoint returns products that match the user's preferences and are within their balance.
- This adds a **personalized shopping experience**.

### 🤝 2. Get 3 Closest Friends with Similar Interests

- A smart social-like feature: find 3 users with similar preferences and closest balances.
- Sorted and filtered using balance comparison logic.
- Encourages community interaction within the e-commerce model.

### 📦 3. Wholesale Buyer Support

- Special endpoint for wholesale buyers (bulk purchases).
- Condition: Must buy products with stock > 1000.
- Includes validation for quantity and balance.

### 🧠 4. Expert Criticism API

- Dedicated endpoint to fetch **expert comments** on a product.
- Includes the expert’s name and email, adding a layer of **credibility** for the product.

### 🎥 5. Content Creator Access

- Users can request **content creation permissions** for a product.
- This opens the door for marketing or influencer-like collaboration.

---

## 👨‍🏫 Developed by

**Hassan Al-Hussaini**  
As part of the Tuwaiq Academy Capstone Bootcamp.

---

## 📝 License

This project is open-source and available under the MIT License.---
