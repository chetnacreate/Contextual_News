# Contextual News Backend

A Spring Boot backend project to retrieve and enrich news articles using LLMs and RESTful APIs.

## Tech Stack
- Java 21
- Spring Boot 3.5.3
- Spring Web, Spring Data JPA
- PostgreSQL / H2
- OpenAI API (Python microservice)

## How to Run
1. Clone the repo
2. Import into IntelliJ
3. Run `ContextualnewsApplication.java`
4. API runs on `http://localhost:8080`

## Endpoints
- `/api/v1/news/search`
- `/api/v1/news/category`
- `/api/v1/news/nearby`

## To Do
- Integrate Python LLM service
- Load data from `news_data.json`
- Implement trending (optional)