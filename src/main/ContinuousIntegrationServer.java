package main;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.ServletException;

import org.json.*;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
public class ContinuousIntegrationServer //extends AbstractHandler
{

    public static String clone_url;
    public static String branch;
    public static String email;
    public static String sha;

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

    ArrayList<File> javaFiles = new ArrayList<>();
    public StringBuilder outputFromCI = new StringBuilder();

    // used to start the CI server in command line
    public static void main(String[] args) throws Exception
    {
//       String sourcDIR = "/Users/Adi/documents/HelloWorld";
//       String cloneURL = "https://github.com/Gabbi68/HelloWorld.git";


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
        DiagnosticCollector diagnosticsCollector = new DiagnosticCollector();

        String filesTOCompiles = "";
        for(File javaFile: javaFiles){

            filesTOCompiles = javaFile.getParent() + java.io.File.separator + javaFile.getName();
            System.out.println(filesTOCompiles = javaFile.getParent() + java.io.File.separator + javaFile.getName());

            OutputStream output = new OutputStream() {
                private StringBuilder string = new StringBuilder();

                @Override
                public void write(int b) throws IOException {
                    this.string.append((char) b );
                }

                //Netbeans IDE automatically overrides this toString()
                public String toString() {
                    return this.string.toString();
                }
            };

            int compileResult = compiler.run(null,null,output, filesTOCompiles);
            if(compileResult == 0){
                outputFromCI.append("Build successful \n");
                outputFromCI.append(output.toString());
            }else {
                outputFromCI.append("Build Failed \n");
                outputFromCI.append(output.toString());
                System.out.println(outputFromCI);

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

        if(outputFromCI.toString().contains("Build successful")){

            for(File javaFile: javaFiles){
                if(javaFile.getName().toLowerCase().contains("test")){
                    String temp = javaFile.getName().replace(".java","");

                    String testFile = temp;

                    outputFromCI.append("================"+testFile+"===================\n");

                    String OS = System.getProperty("os.name").toLowerCase();

                    try{
                        Process pro;
                        //does not work for windows
                        if(OS.contains("win")){
                            pro = Runtime.getRuntime().exec("cmd.exe /c start java "+testFile);
                        }else{
                            //pro = Runtime.getRuntime().exec(javaFile.getParent());
                            pro = Runtime.getRuntime().exec("java -cp "+javaFile.getParent()+" "+testFile);
                        }

                        //The stream obtains data piped from the standard output stream of the process
                        BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                        //The stream obtains data piped from the error output stream of the process
                        BufferedReader error = new BufferedReader(new InputStreamReader(pro.getErrorStream()));


                        String line1 = null;
                        String line2 = null;
                        while ((line1 = input.readLine()) != null) {
                            outputFromCI.append(line1+"\n");
                            //System.out.println(" " + line1);
                        }
                        outputFromCI.append("Error\n");
                        while ((line2 = error.readLine()) != null) {
                            outputFromCI.append(line2+"\n");
                            //System.out.println(" " + line2);
                        }

                        //wait until the process pro has terminated
                        pro.waitFor();
                        //exit value 0 is a successful termination
                        outputFromCI.append("exitValue() "+pro.exitValue());
                        //System.out.println("exitValue() "+pro.exitValue());
                    } catch (Exception e){
                        System.out.println("error");
                    }
                }
            }
        }
    }

    public void getProjectFromGIT(String cloneLink,String branchName, String storeAtPath) {

        String OS = System.getProperty("os.name").toLowerCase();

// TODO Need A Way to catch errors

        if(OS.contains("win")){
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("cmd.exe /c start git clone --branch "+ branchName + " " + cloneLink + " " + storeAtPath, null, new File(System.getProperty("user.home")));

                TimeUnit.SECONDS.sleep(4);

            } catch (Exception e){
                e.printStackTrace();
            }

        }else{
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("git clone --branch "+ branchName + " " + cloneLink + " " + storeAtPath, null, new File(System.getProperty("user.home")));

                //Needs time to clone as it is a separate process so the code keeps running while the cloning is underway
                TimeUnit.SECONDS.sleep(4);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void jsonParser(String str){
//        if (!isJsonString(str)) return;
        JSONObject obj = new JSONObject(str);
        clone_url = obj.getJSONObject("repository").getString("clone_url");
        String ref = obj.getString("ref");
        String[] refpath = ref.split("/");
        branch = refpath[refpath.length - 1];
        sha = obj.getString("after");
        email = obj.getJSONObject("pusher").getString("email");
    }

//    public boolean isJsonString(String str) {
//        try {
//            JSON.parse(str);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }


    public void writeToFile() throws IOException {
        File f = new File("buildHistory.txt");
        if (!f.exists()) {
            PrintWriter writer = new PrintWriter("buildHistory.txt", "UTF-8");
            JSONObject json = new JSONObject();
            JSONObject info = new JSONObject();
            info.put("clone_url", clone_url);
            info.put("branch", branch);
            json.put(sha, info);
            writer.print(json.toString());
            writer.close();
            return;
        }
        if (f.exists() && !f.isDirectory()) {
            Path path = Paths.get("buildHistory.txt");
            String content = Files.readString(path);
            JSONObject json = new JSONObject(content);
            JSONObject info = new JSONObject();
            info.put("clone_url", clone_url);
            info.put("branch", branch);
            json.put(sha, info);
            PrintWriter writer = new PrintWriter("buildHistory.txt", "UTF-8");
            writer.print(json.toString());
            writer.close();
        }
    }
}