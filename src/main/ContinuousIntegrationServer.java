package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
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
 */

/**
 Skeleton of a main.ContinuousIntegrationServer which acts as webhook
 See the Jetty documentation for API documentation of those classes.
 */
public class ContinuousIntegrationServer //extends AbstractHandler
{
    /*
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

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {

        /* COMPILE TESTS
        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.build("C:\\Users\\Martin\\testCompile", "HelloWorld.java");

*/
        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.getProjectFromGIT("https://github.com/Gabbi68/HelloWorld.git","master" , "C:\\Users\\Martin\\testCompile\\HelloWorld");
        TimeUnit.SECONDS.sleep(3);
        run.build("C:\\Users\\Martin\\testCompile\\HelloWorld", "HelloWorld.java");

       /*
        Server server = new Server(8080);
        server.setHandler(new ContinuousIntegrationServer());
        server.start();
        server.join();
        */
    }



    public void build(String souceDIR, String javaFile){


        String filesTOCompiles = souceDIR + File.separator + javaFile;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compileResult = compiler.run(null,null,null,filesTOCompiles);

        if(compileResult == 0){
            System.out.println("Compile success");
        }else {
            System.out.println("Compile failed");
        }

    }


    public void runtests(){

    }

    public void getProjectFormGIT(){}

    public void jsonParser(){

    }


    public void toFile(String commandToRun) throws Exception {

    }

    public void getProjectFromGIT(String cloneLink,String branchName, String storeAtPath){

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd.exe /c start git clone -branch "+ branchName + " " + cloneLink + " " + storeAtPath, null, new File("C:\\Users\\Martin")); //TODO make dynamic

        }catch (IOException e){
            e.printStackTrace();
        }


    }

}