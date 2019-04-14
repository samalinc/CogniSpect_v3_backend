call mvn clean install
cd target
java -jar -Dspring.profiles.active=%SPRING_ENVIRONMENT% cognispect.jar
pause
exit