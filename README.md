# 📚 Library System

Jednoduchá ukázková aplikace v **Java Spring Boot** pro správu knihovny.  
Projekt byl vytvořen jako úkazka pro trainee Junior Java Developer pozici.

---

## 🚀 Spuštění projektu

### 1. Požadavky
- **Java 17+**
- **Maven**
- **PostgreSQL** (na portu 5432)

---

### 2. Nastavení databáze

Aplikace používá tuto konfiguraci (`application.properties`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_system
spring.datasource.username=library_user
spring.datasource.password=password
```

V PostgreSQL je potřeba vytvořit databázi a uživatele.

Spusť **SQL Shell (psql)** a přihlas se jako uživatel `postgres`. Postupně spusť tyto příkazy:

```sql
-- vytvoření databáze a uživatele
CREATE DATABASE library_system;
CREATE USER library_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE library_system TO library_user;

-- přepnutí do nové databáze
\c library_system

-- nastavení práv pro schema public
ALTER SCHEMA public OWNER TO library_user;
GRANT USAGE, CREATE ON SCHEMA public TO library_user;
```

---

### 3. Stažení projektu

```bash
git clone https://github.com/MartinSamo159/library-system.git
cd library-system
```

---

### 4. Spuštění aplikace

```bash
mvn spring-boot:run
```

Aplikace poběží na:

👉 [http://localhost:8080/login.html](http://localhost:8080/login.html)

---

## 🔑 Přihlašovací údaje

- **admin / admin**
- **librarian / librarian**
- **reader / reader**

---

## ✨ Funkcionalita

### 👤 Role a oprávnění
Aplikace má tři uživatelské role s rozdílnými oprávněními:

- **ADMIN**
  - přidávání, editace a mazání **uživatelů**
  - přidávání, editace a mazání **knih**
  - správa **výpůjček** (vytvoření, vrácení)
  - má plný přístup ke všem funkcím

- **LIBRARIAN**
  - přidávání, editace a mazání **knih**
  - správa **výpůjček** (vytvoření, vrácení)
  - **nemůže** spravovat uživatele

- **READER**
  - pouze **náhled** na seznam knih
  - pouze **náhled** na vlastní výpůjčky
  - nemůže přidávat ani editovat data

### 📚 Funkce aplikace
- Evidence knih (CRUD operace, validace, filtrování, stránkování)
- Evidence uživatelů (přístupná jen adminovi)
- Evidence výpůjček a jejich vracení
- Přihlášení přes jednoduchý login formulář
- Autorizace akcí podle role uživatele

---

## 🛠 Technologie

- Java 17
- Spring Boot
- Spring Security
- JPA / Hibernate
- PostgreSQL
