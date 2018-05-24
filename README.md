# Time-Tracking-System
## Project description:
Administrator binds Task to Employee.
Employee could have one or several Tasks.
Employee sets spent time amount for each Task.
Employee can send request to add or remove particular Task.

## Developer:
Dudchenko Andrei
## Mentor:
Lyashenko Maxim

# Prerequisites to run project:
1. To have installed Java JDK
2. To have installed MySQL

# How to run project:
## Deployment:
1. Create "time_tracking" scheme in MySQL using "db_creation.sql" file in root folder or using DBInitializer utility program (see Additional information section below). 
2. Istall maven http://www.apache-maven.ru/install.html
3. In project directory open PowerShell or WinCmd
4. Enter command "mvn tomcat7:run"
5. Open browser and follow the link http://localhost:8888/company/tts

# Additional information:
There is a DBInitializer utility for this project. With it you can easily create/drop required scheme with some mock data.
Initialize class will create "time-tracking" scheme in MySQL (and drop it if it exist).
DeInitialize will drop this scheme from MySQL.

Link to utility:
https://github.com/Lanfirus/Utilities