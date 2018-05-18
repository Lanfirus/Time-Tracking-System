# Time-Tracking-System
## Project description:
Administrator binds Task to Employee.
Employee could have one or several Tasks.
Employee sets spent time amount for each Task.
Employee can send request to add or remove particular Task.

# Developer:
Dudchenko Andrei
# Mentor:
Maxim Lyashenko

# How to run project:
## Manual deployment:
1. Istall maven http://www.apache-maven.ru/install.html
2. In project directory open PowerShell
3. Enter command "mvn tomcat7:run"
4. Open browser and follow the link http://localhost:8888/company/tts/main

## Automatic deployment:
1. Open project in Intelliji IDEA IDE
2. Create new Maven Run/Debug configuration with Command line "verify" and Maven Goal "clean"
3. Check browser path in test.bat file
4. Run this configuration

# Additional information:
There is a DBInitializer utility for this project. With it you can easily create/drop required scheme with some mock data.
Initialize class will create "time-tracking" scheme in MySQL (and drop it if it exist).
DeInitialize will drop this scheme from MySQL.

Link to utility:
https://github.com/Lanfirus/Utilities