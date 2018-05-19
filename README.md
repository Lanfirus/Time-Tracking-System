# Time-Tracking-System
## Project description:
Administrator binds Task to Employee.
Employee could have one or several Tasks.
Employee sets spent time amount for each Task.
Employee can send request to add or remove particular Task.

## Developer:
Dudchenko Andrei
## Mentor:
Maxim Lyashenko

# Prerequisites to run project:
1. To have installed Java JDK
2. To have installed MySQL

# How to run project:
## Manual deployment:
1. Create "time_tracking" scheme in MySQL using "db_creation.sql" file in root folder or using DBInitializer utility program (see Additional information section below). 
2. Istall maven http://www.apache-maven.ru/install.html
3. In project directory open PowerShell
4. Enter command "mvn verify"
5a. Maven will lanuch test.bat file with commands to start Google Chrome browser in Incognito mode. Browser opens on main page of the project.
5b. Open browser and follow the link http://localhost:8888/company/tts/main

##Deployment using Intelliji Idea IDE:
1. Open project in Intelliji Idea IDE
2. Create new Maven Run/Debug configuration with Command line "verify" and Maven Goal "clean"
3. Check browser path in test.bat file
4. Run this configuration

# Additional information:
There is a DBInitializer utility for this project. With it you can easily create/drop required scheme with some mock data.
Initialize class will create "time-tracking" scheme in MySQL (and drop it if it exist).
DeInitialize will drop this scheme from MySQL.

Link to utility:
https://github.com/Lanfirus/Utilities