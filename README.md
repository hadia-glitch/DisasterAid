DisasterAid-Disaster Management System ( Full-Stack Web Application)

Key Features
•	Role-Based User Management:
Three roles: Admin, Volunteer, and Victim – all inheriting from a common User superclass.
Secure login and role-specific registration forms.
Profile management with update and delete options.
•	 Disaster Alerts Module:
Admins can issue alerts with fields: title, description, severity, and location.
Dynamic map integration using OpenStreetMap + Leaflet API to select and display disaster locations visually with popups showing details.
Edit/delete alerts from a unified dashboard interface.
•	Resource Inventory System:
Admins can add and update resources (e.g., food, medicine).
Resource stock is automatically adjusted during team assignments and completions.
•	 Volunteer Dashboard:
Volunteers can update their skills and availability.
View assigned teams and track mission history.
•	Victim Request System:
Victims submit help requests based on disaster type, urgency, and location.
Requests are sorted by priority for efficient response.
•	 Team Assignment & Management:
Admin selects high-priority requests and automatically recommends volunteers based on Skill match	and Availability status
Assigns a team and allocates appropriate resources.
On completion: team is deleted, resources restored, and volunteers marked available.

•	Tech Stack 

Frontend-	HTML5, CSS3, Thymeleaf, JavaScript
Dynamic Map-OpenStreetMap, Leaflet.js – visual location selection
Backend-	Java 17, Spring Boot, Spring MVC, Spring Data JPA
Database-	MySQL 
ORM	Hibernate -(via JPA)
Build & Dependency-	Maven
Development Tools-	IntelliJ IDEA , MySQL Workbench
Version Control-	Git, GitHub



