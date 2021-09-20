package demo.hao;

import demo.hao.api.HelloWorldResource;
import demo.hao.command.MyCommand;
import demo.hao.healthcheck.PingHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addCommand(new MyCommand());
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getDefaultName()
        );
        final PingHealthCheck healthCheck = new PingHealthCheck();
        environment.healthChecks().register("ping", healthCheck);
        environment.jersey().register(resource);
    }

}
