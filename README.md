This is a java based im app which runs an HTTP Server

You can find the openapi documentation in the api.yml file

How to run it:
```
gradle:
 ./gradle run

docker:
 docker build -t im-app .
 docker run -dp 8080:8080 im-app
```


