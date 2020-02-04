package main;
/*
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
*/
import javax.tools.*;
import java.io.*;
import java.lang.Object;
import java.util.*;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
/*
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.*;


/**
 Skeleton of a main.ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
 */
public class ContinuousIntegrationServer {   
 
    /*
    public static String clone_url;
    public static String branch;
    
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
    */


    ArrayList<File> javaFiles = new ArrayList<>();

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
        String[] mails = {"nicolai.hellesnes@gmail.com"};
        String output = "hej";
        SendMail test = new SendMail(mails, output);
        //test.SendMail(mails, output);

       /*
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer());
        server.start();
        server.join();
        */
    }


    public void build(String souceDIR){

        File folder = new File(souceDIR);
        listFilesForFolder(folder);


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        String filesTOCompiles = "";
        for(File javaFile: javaFiles){

            filesTOCompiles = javaFile.getParent() + java.io.File.separator + javaFile.getName();
            System.out.println(filesTOCompiles = javaFile.getParent() + java.io.File.separator + javaFile.getName());

            int compileResult = compiler.run(null,null,null, filesTOCompiles);
            if(compileResult == 0){
                System.out.println("Compile success");
            }else {
                System.out.println("Compile failed");
            }

        }



    }


    public void listFilesForFolder(File folder) {
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(fileEntry.getName().endsWith(".java")){
                    javaFiles.add(fileEntry);
                }
            }
        }
    }


    public void runtests(){

    }

    public void getProjectFormGIT(){}
    /*
    public void jsonParser(String str){
      if (!isJsonString(str)) return;
      JSONObject obj = new JSONObject(str);
      JSONObject repo = obj.getJSONObject("repository");
      clone_url = repo.getString("clone_url");
      String[] ref = string.split("/");
      branch = ref[ref.length - 1];
    }

    public boolean IsJsonString(str) {
      try {
          JSON.parse(str);
      } catch (e) {
          return false;
      }
      return true;
    }
    */



    public void toFile(String commandToRun) throws Exception {

    }
  
  

    public void getProjectFromGIT(String cloneLink,String branchName, String storeAtPath){

        String OS = System.getProperty("os.name").toLowerCase();


        if(OS.contains("win")){

            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd.exe /c start git clone --branch "+ branchName + " " + cloneLink + " " + storeAtPath, null, new File(System.getProperty("user.home")));

                TimeUnit.SECONDS.sleep(3);

            }catch (Exception e){
                e.printStackTrace();
            }


        }else if (OS.contains("sunos")){


            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("git clone --branch "+ branchName + " " + cloneLink + " " + storeAtPath, null, new File(System.getProperty("user.home")));

                //Needs time to clone as it is a separate process so the code keeps running while the cloning is underway
                TimeUnit.SECONDS.sleep(3);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
