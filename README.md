# Footgo
[FootGo amateur soccer leageue](https://footgo-league.com/)

Requirements: 

1. MySQL with schema 'footgo'

2. Java 8 or higher

3. Maven 3 or higher


How to setup locally:
 1. In directory src/main/resources/ copy file application.properties.example to the same directory with a name application.properties
 
 2. In application.properties file specify your MySQL username and password
 
 3. Additionally you can specify your email login/password. You may need to setup your mail provider. Refer to https://support.google.com/accounts/answer/6010255?hl=en for example. 
 
 4. Execute 'mvn clean install -Dskip.tests=true' in root repository directory.
 
 5. Run application mvn spring-boot:run'
 