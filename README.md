# Gradebook

## Table of Contents
1. [Tech stack](#tech-stack)  
2. [Preparation](#preparation)
3. How to run:
   1. [dev](#how-to-run-dev)
   2. [production](#how-to-run-production)

---

## Tech stack
* Java 21
* Spring Boot 3.2.3
* Thymeleaf
* Hibernate
* MariaDB (MySQL)
* H2
* Preline UI
* DaisyUI
* Tailwind CSS

## Preparation
1. To use MySQL (MariaDB) database:
   * create `pai_jee_final` database
   > NOTE: your DBMS has to has user with name **root** and _empty_ password. Instead, change `spring.datasource.url` in [application.properties](src/main/resources/application.properties)
2. To use H2 (in-memory) database:
   1. go to [application.properties](src/main/resources/application.properties)
   2. comment all `##ustawienia SQL` lines
   3. uncomment all `#ustawienia memory H2` lines

## How to run (dev)
1. Open terminal.
2. Type `npm run dev`
3. Build & run project.

## How to run (production)
1. Open terminal.
2. Type `npm run prod`
3. Build & run project.