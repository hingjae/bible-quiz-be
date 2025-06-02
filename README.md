# Bible Quiz Backend

Spring Boot REST API for Bible Quiz Application, deployed on AWS Elastic Beanstalk.

---

## 🚀 Features

- RESTful API for Bible quiz management
- MySQL-backed persistent storage
- AWS Elastic Beanstalk deployment
- CloudWatch logging integration
- CI/CD with GitHub Actions

---

## 🛠 Tech Stack

- Java 21
- Spring Boot 3
- MySQL 8
- AWS Elastic Beanstalk
- GitHub Actions (CI/CD)
- CloudWatch Logs

---

## 🚧 Setup & Deployment (Local)

Configure database settings via application.yml or environment variables:

- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD

```bash
# Build project
./gradlew build

# Run locally (dev profile)
java -jar build/libs/app-1.0.0.jar
```

---

## 📊 Monitoring
Application logs streamed to AWS CloudWatch

/var/log/web.stdout.log and /var/log/web.stderr.log tracked automatically

---

## 📄 License

This project is licensed under the [MIT License](./LICENSE).