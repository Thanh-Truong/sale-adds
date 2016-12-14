package example.resource;
import java.io.InputStream;

import example.config.MessagesConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;

@Path(value = "/puzzle")
public class PuzzleResource {

    private final MessagesConfiguration conf;

    public PuzzleResource(final MessagesConfiguration conf) {
        this.conf = conf;
    }

    /*@GET
    public String sayHello() {
        //return conf.getInstruction();
		return "<html> <h1>AAAAAAA</h1> <html>";
    }*/
	
	@GET
	@Produces({MediaType.TEXT_HTML})
    public InputStream  sayHello() {
		return getClass().getResourceAsStream("/puzzle.html"); 
    }
}
