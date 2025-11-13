Rapport Technique – Agent AI basé sur Spring Boot et Telegram
1. Architecture générale

Le projet est composé de deux sous-projets Spring Boot distincts :

a) MCP-Server

Rôle : Fournir les tools liés au système d’information (ex : gestion des étudiants, RH…) via le protocole MCP (Model Context Protocol).

Composant principal : McpTools

Contient toutes les méthodes annotées @McpTool, par exemple :

getAllStudents() : retourne tous les étudiants et leurs modules

getStudentByName(String name) : recherche un étudiant

getAverageNoteByStudent(String name) : calcul de la moyenne

getTopStudents(int top) : liste des meilleurs étudiants

Dépendances principales :

spring-boot-starter-web : expose les endpoints HTTP

spring-ai : gestion des interactions avec LLM / outils AI

spring-ai-mcp-server : serveur MCP pour publier les tools

b) AIAgent

Rôle : Consommer les tools exposés par MCP-Server et fournir une interface conversationnelle via Telegram ou un navigateur web.

Composants clés :

AIAgent : Agent AI principal qui utilise Llama 3.1 local (modèle gratuit) pour générer des réponses.

TelegramService : Gère la communication avec les utilisateurs via Telegram.

WebController : Permet de tester les interactions via navigateur.

Dépendances principales :

spring-boot-starter-web : pour les endpoints REST et le serveur web

spring-ai : pour gérer le ChatClient, advisors, mémoire et tools

spring-ai-mcp-client : pour consommer le MCP-Server et utiliser les tools

telegram : pour l’intégration du bot Telegram

2. Flux de communication

Utilisateur pose une question via Telegram ou le navigateur web.

La question est envoyée à AIAgent qui gère la session.

AIAgent interroge le MCP-Server pour exécuter les tools nécessaires.

Le résultat (texte, éventuellement calcul ou info) est renvoyé à l’utilisateur.

Le modèle Llama 3.1 peut enrichir la réponse avec du contexte et des conseils.

3. Points techniques clés

MCP (Model Context Protocol) : permet de séparer la logique métier (tools) de l’agent AI.

Tools : toutes les méthodes importantes annotées @McpTool pour exposer les fonctionnalités du système.

LLM local : utilisation de Llama 3.1 en local, gratuit, évitant la dépendance à une API cloud.

Communication multi-canaux : texte via Telegram ou navigateur, future extension pour audio et images.

Architecture future : possibilité d’intégrer des microservices avec gateway, Eureka, config server, tolérance aux pannes.
