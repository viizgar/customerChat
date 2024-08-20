# Customer Chat <Proof of Concept>

## What is this project

This project contains a simple implementation for a web-based customer service chat using Kotlin as the programming
language and Spring Boot as the web framework.

## What it does

The project scope is a simple CRUD REST server that allows clients to create chat rooms or sessions and send messages
between customers and service agents.

## How to interact

Using HTTP REST verbs following the RESTful principles.
Details can be found in the OpenAPI documentation:

- [customerChat-chat-openapi.yaml](src/main/resources/static/customerChat-openapi_1.yaml)
- [customerChat-user-openapi.yaml](src/main/resources/static/customerChat-openapi_2.yaml)

## Possible future extensions

The project implementation only deals with the most basic functionalities of a customer chat software.
and doesn't include several concepts that would be needed to be considered production ready.
Here is a list of possible extensions that could be implemented:

1. **Containerization (Docker) and TestContainers**:
   Incorporate Docker to facilitate project deployment, scalability, and isolation, and to make the project
   and prepare it for a possible future cloud deployment (e.g. with Kubernetes).
2. **Complete end-to-end testing coverage**:
   Essential to verify the full functionality of the system,
   ensure that the business logic is correct, and therefore increase confidence in deployments.
3. **Public OpenAPI Documentation (Swagger)**.
   Publishing the OpenAPI documentation will improve the development experience for those working with the project.
   For example, front-end developers.
4. **Authentication and Authorization (JWT)**.
   For a production-ready application, it would be really important to include authentication of all users to access the
   system.
   Also protect the different entry points depending on user permissions.
   This could be implemented using a Json Web Token (JWT) standard.
4. **The presentation layer (frontend)**
   The scope of the project does not include the presentation layer, but it would be necessary to
   to provide its services to end users.
   This layer could be implemented as an SPA using React and possibly React Native to provide a better UX on mobile
   devices.
5. **On-Disk Persistence Layer**
   Another important point. Currently, the database is an embedded in-memory managed (H2).
   This would need to be updated to use an on-disk, independent database like Postgresql.
6. **Real-Time Communication**
   To provide a realistic chat experience, a bidirectional channel for messages would need to be implemented between
   clients and these services. This is currently outside the scope of the project, but it would be interesting to have
   for a production-ready solution.
   WebSockets could be used to implement this two-way channel communication.
7. **Scalability**
   Related to Docker and Kubernetes. Including a load balancing and autoscaling implementation would help the project to
   scale and perform while serving a large number of concurrent users.
8. **Additional business cases**.
    - Binary compatibility (images, audio, video, documents)
    - Message history for each logged-in user
    - Search capabilities
    - Message replies
    - Emoticons
    - Archiving of chat sessions
    - Feedback mechanism (users rate their experience)
    - Integration with third party applications such as CRM or ticket systems
9. **Analytics, logging and reporting**
   To track and analyze metrics such as message volume, response times, and user engagement to optimize chat performance
   and user satisfaction.
10. **Localization**:
    To Provide automatic translation or support for multiple languages to enable global deployment.
11. **Security and Encryption**:
    Being compliant with GDPR and include end-to-end message encryption, audit logs
12. **Chatbots** (AI):
    Integrate chatbots for automated responses, FAQ handling, and AI-driven customer support.
