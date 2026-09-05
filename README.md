# swingBasicGUI — Java Swing Application (Employee Detail / CRUD)

A **standalone Java Swing application** built to understand **Java GUI development and MVC structure**. It performs full **CRUD operations** on an Employee Detail form, backed by a database.

The application was later enhanced to connect to a **remote database via a reverse proxy (ngrok)**, allowing it to be distributed and run from a `.jar` file on different machines without direct/local database access. This mirrors how a reverse proxy secures backend access in production systems: it tunnels client requests to the server, hiding the origin IP and avoiding direct DB exposure.

> This is a basic, learning-focused project — not a production application. It was built to understand fundamentals (Java GUI + MVC + remote DB access patterns).

## Tech Stack

- **Language:** Java
- **GUI Toolkit:** Swing (part of the standard JDK)
- **Architecture:** MVC (Model-View-Controller)
- **Database:** JDBC-based DB connection (PostgreSQL)
- **Remote DB access:** ngrok (reverse proxy / secure tunnel), enabling remote DB connectivity without exposing the DB directly
- **Distribution:** Packaged and run as a standalone `.jar` file
- **IDE used:** IntelliJ IDEA (`.idea/` config folder is present in the repo)
- **Build tool:** None (no `pom.xml` / `build.gradle` found) — this is a plain Java source project

## What It Does

- Displays a GUI form for employee details (built with Swing, following MVC structure)
- Performs full CRUD operations (Create, Read, Update, Delete) against a connected database
- Originally connected to a local DB; later reconfigured to connect to a **remote DB via ngrok reverse proxy**, so the app could be distributed as a `.jar` and run on different machines while the DB stayed centrally hosted and protected

> Note: `Main.java` only directly instantiates `menuMethod` — the CRUD classes (`creationOfEmployeeDetail`, `readingOfEmployeeDetails`, `viewAllRecords`, `updationOfEmployeeDetail`, `searchDelete`) are commented out there, meaning they're most likely invoked from *within* `menuMethod` (e.g. via button clicks or menu selections) rather than all at startup — which is the expected pattern for a menu-driven GUI.

## Project Structure

```
swingBasicGUI/
├── .idea/               # IntelliJ IDEA project settings
├── src/
│   └── employeeDetail/  # Source files for the Employee Detail GUI
│       ├── Main.java                    # Entry point — public static void main()
│       ├── menuMethod.java              # Main menu / navigation logic
│       ├── creationOfEmployeeDetail.java # Create (Add) employee record
│       ├── readingOfEmployeeDetails.java # Read a single employee record
│       ├── viewAllRecords.java          # Read — view all employee records
│       ├── updationOfEmployeeDetail.java # Update employee record
│       └── searchDelete.java            # Search / Delete employee record
└── .gitignore
```

> ⚠️ Note: `.idea/` is normally excluded from git (it's local IDE config), but it appears to be committed here. It's not required for the app to run — you can ignore or delete it if you're not using IntelliJ.

## Prerequisites

- JDK installed (Java 8+, since Swing is part of the standard library)
- A running database instance matching what the code connects to (confirm type/name here: `_____________`)
- The appropriate JDBC driver `.jar` on the classpath (e.g. MySQL Connector/J, PostgreSQL JDBC driver — depends on which DB is used)
- If reconnecting via the remote/ngrok setup: an active ngrok tunnel pointing to the DB host, with the connection string in the code updated to match (ngrok URLs are temporary/regenerate on restart unless using a paid static domain)
- Optionally, IntelliJ IDEA (since project config is already set up for it)

## How to Run

### Option A — Open in IntelliJ IDEA (easiest, since `.idea/` config exists)
1. Open IntelliJ IDEA
2. `File → Open` and select the `swingBasicGUI` folder
3. Let IntelliJ index the project
4. Locate `Main.java` inside `src/employeeDetail`
5. Right-click it → **Run** (runs the `main()` method inside `Main`)

### Option B — Compile and run manually from terminal
```bash
cd swingBasicGUI/src
javac employeeDetail/*.java
java employeeDetail.Main
```

## Will It Run As-Is?

Not quite out-of-the-box — a few things need to be in place first, since this project doesn't use Maven/Gradle to manage dependencies or config:

1. **Database connection details** — the DB URL, username, and password are likely hardcoded somewhere in the source. If the code still points to the original ngrok tunnel URL used during development, that tunnel is almost certainly no longer active (ngrok URLs expire when the session ends). You'd need to either spin up your own local DB and update the connection string, or set up a fresh reverse proxy tunnel.
2. **JDBC driver** — the correct driver `.jar` needs to be added to the classpath manually (IntelliJ project settings, or via `-cp` on the command line), since there's no build tool to pull it automatically.
3. **Entry point** — confirmed: run `employeeDetail.Main` (this class contains `main()` and also references a supporting `menuMethod` class in the same package).

Once those are sorted, since Swing itself ships with the JDK, there's nothing else external to install. If you have the original `.jar` file (mentioned as the distribution method), that may run standalone if its embedded DB reference still resolves — otherwise it'll need the same reconfiguration as above.
