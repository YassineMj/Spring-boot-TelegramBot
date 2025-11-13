# Agent AI Spring Boot + Telegram

Un Agent AI simple basé sur **Spring Boot**, **Telegram** et **Ollama local gratuit**.

## 1. Description

L’idée : avoir un **système d’information** pour gérer n’importe quel domaine (ex : RH, étudiants…) et utiliser un **bot Telegram** pour poser des questions sur le système.

## 2. Fonctionnalités

- Communication **texte** via Telegram ou navigateur
- Intégration future : **audio, images**, et **architecture microservices** (gateway, Eureka, config, tolérance aux pannes, etc.)  
- Utilisation d’un **LLM local gratuit (Ollama)**  

## 3. Architecture Technique

### Projets

1. **MCP-Server**  
   - Contient les tools du système (`McpTools`)  
   - Dépendances : `spring-boot-starter-web`, `spring-ai`, `spring-ai-mcp-server`  

2. **AIAgent**  
   - Consomme le MCP-Server et utilise `Llama 3.1` local  
   - Gère la conversation via `TelegramService`  
   - Testable via navigateur avec `WebController`  
   - Dépendances : `spring-boot-starter-web`, `spring-ai`, `spring-ai-mcp-client`, Telegram  

### Flux de communication
Utilisateur → Telegram/Web → AIAgent → MCP-Server (Tools) → AIAgent → Réponse

### Tools disponibles

- `getAllStudents()` : liste des étudiants  
- `getStudentByName(name)` : recherche par nom  
- `getAverageNoteByStudent(name)` : moyenne d’un étudiant  
- `getTopStudents(top)` : meilleurs étudiants  
- `getModulesAboveThreshold(name, seuil)` : modules au-dessus d’une note  
- `getGlobalAverage()` : moyenne globale

