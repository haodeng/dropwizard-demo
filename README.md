# dropwizard-demo
Try out dropwizard, see how it works. 

    mvn package
    java -jar ./target/dropwizard-demo-1.0-SNAPSHOT.jar server

Test

     curl http://localhost:8080/hello-world
     curl 'http://localhost:8080/hello-world?name=hao'

    curl -v -X DELETE 'http://localhost:8080/hello-world/name/hao' 
    404

    curl -v -X PUT -H "Content-Type: application/json" -d '{"name":"hao"}' 'http://localhost:8080/hello-world'
    201

    curl -v -X PUT -H "Content-Type: application/json" -d '{"name":"hao"}' 'http://localhost:8080/hello-world'
    200

    curl -v -X PUT -H "Content-Type: application/json" -d '{"name":" "}' 'http://localhost:8080/hello-world'
    422

    curl -v -X DELETE 'http://localhost:8080/hello-world/name/hao'
    200

Run as command

    java -jar ./target/dropwizard-demo-1.0-SNAPSHOT.jar mycommand -u hao
    output: Hello hao

