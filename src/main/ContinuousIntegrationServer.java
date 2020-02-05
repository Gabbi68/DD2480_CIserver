package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.*;

import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;

/**
 Skeleton of a main.ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
 */
public class ContinuousIntegrationServer extends AbstractHandler
{
    public static String clone_url;
    public static String branch;
    public static String email;
    public static String sha;

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        System.out.println(target);

        // here you do all the continuous integration tasks
        // for example
        // 1st clone your repository
        // 2nd compile the code

        response.getWriter().println("CI job done");
    }

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer());
        server.start();
        server.join();
    }



    public void build(){

    }


    public void runtests(){

    }

    public void getProjectFormGIT(){}

    public void jsonParser(String str){
      if (!isJsonString(str)) return;
      JSONObject obj = new JSONObject(str);
      clone_url = obj.getJSONObject("repository").getString("clone_url");
      String ref = obj.getString("ref");
      String[] refpath = ref.split("/");
      branch = refpath[refpath.length - 1];
      sha = obj.getString("after");
      email = obj.getJSONObject("pusher").getString("email");
    }

    public boolean isJsonString(String str) {
      try {
          JSON.parse(str);
      } catch (Exception e) {
          return false;
      }
      return true;
    }


    public void writeToFile() throws IOException {
      File f = new File("buildHistory.txt");
      if(!f.exists()) {
          PrintWriter writer = new PrintWriter("buildHistory.txt", "UTF-8");
          JSONObject json = new JSONObject();
          JSONObject info = new JSONObject();
          info.put("clone_url", clone_url);
          info.put("branch", branch);
          json.put(sha, info);
          return;
      }
      if(f.exists() && !f.isDirectory()) {
        String content = Files.readString("buildHistory.txt");
        JSONObject json = new JSONObject(content);
        JSONObject info = new JSONObject();
        info.put("clone_url", clone_url);
        info.put("branch", branch);
        json.put(sha, info);
        FileWriter fileWriter = new FileWriter("buildHistory.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(json.toString());
        printWriter.close();
      }
    }

}
