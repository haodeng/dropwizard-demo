# dropwizard-demo
Try out dropwizard, see how it works. 

    mvn package
    java -jar ./target/ dropwizard-demo-1.0-SNAPSHOT.jar server

Test

     curl http://localhost:8080/hello-world
     curl 'http://localhost:8080/hello-world?name=hao'

