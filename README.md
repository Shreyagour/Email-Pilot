# Chrome Extension with Spring Boot Backend & React (Vite) Frontend

## 📝 Overview
The project enables a seamless integration between the Chrome Extension and the backend services, allowing users to perform various operations efficiently.


## 🚀 Features
- 🖥 **Spring Boot Backend**: Handles API requests and business logic.
- 🌐 **React (Vite) Frontend**: Provides an intuitive user interface.
- 🛠 **Chrome Extension**: Enhances browser functionality with additional features.
- 🔄 **RESTful APIs**: Secure communication between frontend, backend, and extension.

## 🛠 Setup Instructions

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/yourusername/your-repo.git
cd your-repo
```

### **2️⃣ Backend Setup (Spring Boot)**
#### **Prerequisites:**
- Java 17+
- Maven
- PostgreSQL / MySQL (Optional)

#### **Steps:**
```sh
cd backend
mvn clean install
mvn spring-boot:run
```

The backend server should be running at `http://localhost:8080`

### **3️⃣ Frontend Setup (React Vite)**
#### **Prerequisites:**
- Node.js & npm/yarn

#### **Steps:**
```sh
cd frontend
npm install   # or yarn install
npm run dev
```

The frontend should be running at `http://localhost:5173`

### **4️⃣ Chrome Extension Setup**
#### **Steps to Load in Chrome:**
1. Open **Google Chrome**.
2. Navigate to `chrome://extensions/`.
3. Enable **Developer Mode** (top-right corner).
4. Click **Load unpacked** and select the `extension/` folder.
5. The extension should now be available in the browser!


## 🛠 Technologies Used
- **Backend**: Spring Framework, Spring Boot REST, Spring WebClient, Java
- **Frontend**: React.js (Vite), Axios
- **Chrome Extension**: JavaScript (ES6), Manifest v3



