This is a java based instant message app which runs an HTTP Server

The database is a one file demo one which you can find in the sample.db file

You can find the openapi documentation in the api.yml file

How to run it:
```
gradle:
 ./gradle run

docker:
 docker build -t im-app .
 docker run -dp 8080:8080 im-app
```