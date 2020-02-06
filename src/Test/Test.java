package Test;

import main.ContinuousIntegrationServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    //=============Test Cases Compile==================

    public void testCompileSuccess(){

        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.build("./Test/HelloWorld");

        if(run.outputFromCI.toString().contains("Success")){
            System.out.println("Test is Successful");
        }else{
            System.out.println("Test Failed");
        }

    }


    public void testCompileFailed(){

        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.build("./Test/failHelloWorld");

        if(run.outputFromCI.toString().contains("Failed")){
            System.out.println("Test is Successful");
        }else{
            System.out.println("Test Failed");
        }


    }


    //=============Test Cases Get from git==================

    public void testGetFromGitSuccess(){




    }

    public void testGetFromFailed(){

    }

    //=============Test Cases Email==================



    //=============Test Cases Run test==================



    //=============Test Cases JSON==================

    /*
     * tests jsonParser function
     */
    public void testJSONParsing() throws IOException {
        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        Path path = Paths.get("exampleJSON.txt");
        String content = Files.readString(path);
        run.jsonParser(content);
        if (run.clone_url.equals("https://github.com/Codertocat/Hello-World.git")) {
            if (run.branch.equals("simple-tag")) {
                if (run.email.equals("21031067+Codertocat@users.noreply.github.com")) {
                    if (run.sha.equals("0000000000000000000000000000000000000000")) {
                        System.out.println("Test Passed");
                        return;
                    }
                }
            }
        }
        System.out.println("Test Failed");
    }

    /*
     * tests writeToFile function
     */
    public void testWriteToFile() throws IOException {
        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.clone_url = "https://github.com/Codertocat/Hello-World.git";
        run.branch = "simple-tag";
        run.email = "21031067+Codertocat@users.noreply.github.com";
        run.sha = "0000000000000000000000000000000000000000";
        run.writeToFile("testHistory.txt");
        run.sha = "1000000000000000000000000000000000000001";
        run.writeToFile("testHistory.txt");
        Path examplePath = Paths.get("exampleHistory.txt");
        String example = Files.readString(examplePath);
        Path testPath = Paths.get("testHistory.txt");
        String test = Files.readString(testPath);
        if (example.equals(test)) {
            System.out.println(("Test Passed"));
        } else {
            System.out.println("Test Failed");
        }
    }

    //=============Test Cases Compile==================


    //=============Test Cases Compile==================
}
