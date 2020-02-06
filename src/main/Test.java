//package Test;

//import ContinuousIntegrationServer.*;

public class Test {

    //=============Test Cases Compile==================

    public void testCompileSuccess(){

        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.build("./HelloWorld");

        if(run.outputFromCI.toString().contains("Success")){
            System.out.println("Test is Successful");
        }else{
            System.out.println("Test Failed");
        }

    }


    public void testCompileFailed(){

        ContinuousIntegrationServer run = new ContinuousIntegrationServer();
        run.build("./failHelloWorld");

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

    public void testJSONParsing() {

    }

    public void testWriteToFile() {

    }

    //=============Test Cases Compile==================


    //=============Test Cases Compile==================
}
