mvnw clean

mvnw package

docker build -t vault/namespace-test .


docker run -p 8080:8080  vault/namespace-test

