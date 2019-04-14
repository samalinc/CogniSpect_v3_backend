call heroku container:login
call heroku container:push web -a cognispect
call heroku container:release web -a cognispect