# Task Manager

## Projektbeschreibung

Das **Task Manager Projekt** ist eine vollständige Anwendung, die sowohl ein Backend als auch ein Frontend umfasst. Die Anwendung ermöglicht es Benutzern, Aufgaben zu erstellen, zu verwalten, zu aktualisieren und zu löschen. Sie dient als Beispiel für eine moderne, skalierbare Architektur, die in Docker-Containern verpackt und auf Plattformen wie Railway und Vercel bereitgestellt wird. 

Dieses Projekt beinhaltet:
- Ein **Spring Boot**-Backend für die Geschäftslogik und Datenbankintegration.
- Ein **React/TypeScript**-Frontend für die Benutzeroberfläche.
- Docker-Integration für eine einfache Bereitstellung.
- Deployment des Backends und der PostgreSQL-Datenbank auf **Railway**.
- Deployment des Frontends auf **Vercel**.

---

## Technologien

- **Backend**: 
  - Framework: Spring Boot
  - Datenbank: PostgreSQL
  - Containerisierung: Docker
  - Deployment: Railway

- **Frontend**:
  - Framework: React (verwendet TypeScript und Tailwind CSS)
  - Build-Tool: Vite
  - Deployment: Vercel

---

## Projektstruktur

### Backend

Das Backend ist in **Spring Boot** geschrieben und enthält die folgende wichtige Struktur:
- `Controller`: Nimmt HTTP-Anfragen entgegen und gibt JSON-Antworten zurück.
- `Service`: Implementiert die Geschäftslogik der App.
- `Repository`: Kommuniziert mit der PostgreSQL-Datenbank.

#### Verzeichnisse
1. `src/main/java`: Enthält den gesamten Java-Quellcode.
2. `Dockerfile`: Definiert den Docker-Container für das Backend.
3. `application.properties`: Konfigurationsdetails wie Datenbankverbindungen.

### Frontend

Das Frontend nutzt **React** und ist in **TypeScript** geschrieben.
- Es enthält Ansichten für das Dashboard, das Aufgabenmanagement und Formulare zur Bearbeitung von Aufgaben.
- Vercel hostet das Frontend und stellt sicher, dass Nutzer weltweit schnellen Zugriff haben.

#### Verzeichnisse
1. `src`: Enthält alle Frontend-Komponenten, wie Seiten, Services und Assets.
2. `vite.config.ts`: Konfiguration für das Build-Tool Vite.
3. `tailwind.config.js`: Konfiguration für Tailwind CSS.

---

## Datenbank

Die Anwendung verwendet PostgreSQL, eine relationale Datenbank. 
- Das Schema wird automatisch generiert durch Hibernate, das als ORM genutzt wird.
- Beispiel für eine Aufgabe in der Datenbank:
  ```sql
  CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
  );
  ```

---

## Deployment und Docker

### Backend und Datenbank

Das Backend und die PostgreSQL-Datenbank sind beide in einem einzigen **Dockerfile** enthalten. Es wird auf Railway bereitgestellt:
1. **Dockerfile**:
   ```dockerfile
   FROM openjdk:17-alpine
   COPY ./target/task-manager.jar app.jar
   ENTRYPOINT ["java", "-jar", "/app.jar"]
   ```
2. **Deployment auf Railway**:
   - Das Backend wird auf Railway gepusht.
   - PostgreSQL-Server ist ebenfalls auf Railway konfiguriert.

### Frontend

Das Frontend wird mit einer separaten **Dockerfile** verpackt und auf Vercel deployed:
```dockerfile
# Wählt ein leichtes Node.js-Image
FROM node:16
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
```

---

## Endpunkte des Backends

Liste der wichtigsten API-Endpunkte:
1. **GET /tasks**: Liefert alle Aufgaben.
2. **POST /tasks**: Erstellt eine neue Aufgabe.
3. **GET /tasks/{id}**: Liefert Details zu einer bestimmten Aufgabe.
4. **PUT /tasks/{id}**: Aktualisiert eine Aufgabe.
5. **DELETE /tasks/{id}**: Löscht eine Aufgabe.

