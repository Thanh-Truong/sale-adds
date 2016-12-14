package example;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import example.config.ExampleServiceConfiguration;
import example.resource.PuzzleResource;
import example.resource.SolveResource;

public class ExampleService extends Service<ExampleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleService().run(args);
    }

    @Override
    public void initialize(final Bootstrap<ExampleServiceConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-example");
    }

    @Override
    public void run(final ExampleServiceConfiguration conf, final Environment env) throws Exception {
        env.addResource(new PuzzleResource(conf.getMessages()));
        env.addResource(new SolveResource(conf.getMessages()));
    }

}
