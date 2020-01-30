package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.tools.*;
import java.io.File;
import java.lang.Object;


import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
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

        ContinuousIntegrationServer run = new ContinuousIntegrationServer();

        run.build("C:\\Users\\Martin\\testCompile", "HelloWorld.java");


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


    public void toFile(){}

}