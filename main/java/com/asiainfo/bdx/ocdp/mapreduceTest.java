package com.asiainfo.bdx.ocdp;

import java.io.IOException;

/**
 * Created by baikai on 11/10/16.
 */
public class mapreduceTest {
    public static void main(String[] args) {
        if (args.length != 5){
            System.out.println("Please verify parameters, need 5 parameters: \n " +
                    "1) yarn resource manager \n " +
                    "2) hdfs fs default \n " +
                    "3) mapreduce jar \n " +
                    "4) input path \n" +
                    "5) output path");
            return;
        }
        MapReduceClient client = new MapReduceClient(args[0], args[1]);
        System.out.println("Yarn resource manager is: " + args[0]);
        System.out.println("HDFS fs default is: " + args[1]);
        System.out.println("MapReduce jar file is: " + args[2]);
        System.out.println("Input path is: " + args[3]);
        System.out.println("Output path is: " + args[4]);
        try{
            client.submitMRJob(args[2], args[3], args[4]);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
