package example.resource;
import java.io.InputStream;

import example.config.MessagesConfiguration;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import example.solver.PuzzleSolver;

@Path(value = "/solve")
public class SolveResource {

    private final MessagesConfiguration conf;
    private final int NUM_ROWS = 3;

    public SolveResource(final MessagesConfiguration conf) {
        this.conf = conf;
    }

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response solveFile(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String output = "Uploaded file " + fileDetail.getFileName();
		try {
			PuzzleSolver ps = read(uploadedInputStream);
			output += ps.run();
		}  
		catch(Exception e) {
		    System.out.println(e);
		}
		return Response.status(200).entity(output).build();
	}
	
	/*Re-direct to the main page, in case the page is refreshed*/
	@GET
    public Response redirectToMainPage() {
		try {
	        java.net.URI location = new java.net.URI("/puzzle");
	        return Response.temporaryRedirect(location).build();
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return Response.status(404).entity("Error").build();
    }
	/*I don't know which is the best way to convert a List to array of primitive
	 * data*/
	public static int[] toArray(List<Integer> values) {
		if (values == null || values.size() == 0) return null;
		int[] result = new int[values.size()];
		for(int i = 0; i < values.size(); i++) {
			result[i] = values.get(i);
		}
		return result;
	}
	public static String[] toArrayString(List<String> values) {
		if (values == null || values.size() == 0) return null;
		String[] result = new String[values.size()];
		for(int i = 0; i < values.size(); i++) {
			result[i] = values.get(i);
		}
		return result;
	}
	
	private PuzzleSolver read(InputStream input) {
		String val = "<br>";
		BufferedReader r = null;
		String separator = ",";
		boolean success = false;
		int total_inventory = 0;
	    
		List<Integer> impressions = new ArrayList<Integer>();
	    List<Integer> prices = new ArrayList<Integer>();
	    List<String> companies = new ArrayList<String>();
		try {
			r = new BufferedReader(new InputStreamReader(input));		    
		    total_inventory = Integer.parseUnsignedInt(r.readLine());
            int i = 0;
		    String line;
		    while((line = r.readLine()) != null) {
                String[] items = line.split(separator);
                if (items.length != NUM_ROWS){
                	break;
                }
                companies.add(items[0]);
                impressions.add(Integer.parseUnsignedInt(items[1]));
                prices.add(Integer.parseUnsignedInt(items[2]));
 		        i++;
		    }
		    success = true;
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	    return success ? (new PuzzleSolver(total_inventory, toArrayString(companies),
	    		toArray(impressions), toArray(prices), prices.size())): null;
    }
}
