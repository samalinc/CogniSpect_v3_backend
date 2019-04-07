call mvn clean install
cd target
java -jar -Dspring.profiles.active=%SPRING_ENVIRONMENT% cognispect-0.0.1-SNAPSHOT.jar
pause
exit