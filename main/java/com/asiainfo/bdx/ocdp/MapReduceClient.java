package com.asiainfo.bdx.ocdp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.examples.QuasiMonteCarlo;
import org.apache.hadoop.examples.QuasiMonteCarlo.QmcMapper;
import org.apache.hadoop.examples.QuasiMonteCarlo.QmcReducer;
import org.apache.hadoop.examples.WordCount;
import org.apache.hadoop.examples.WordCount.IntSumReducer;
import org.apache.hadoop.examples.WordCount.TokenizerMapper;

import java.io.IOException;

/**
 * Created by baikai on 11/10/16.
 */
public class MapReduceClient {

    private Configuration conf;

    public MapReduceClient(String yarnRM, String fsDefault){
        conf = new Configuration();
        conf.set("yarn.resourcemanager.address", yarnRM);
        conf.set("mapreduce.framework.name", "yarn");
        conf.set("fs.default.name", fsDefault);
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("hadoop.security.authentication", "Kerberos");
        conf.set("yarn.resourcemanager.principal", "rm/_HOST@ASIAINFO.COM");
        //conf.addResource(new Path("/etc/hadoop/conf/mapred-site.xml"));
        //conf.addResource(new Path("/etc/hadoop/conf/core-site.xml"));

        this.authentication(conf);
    }

    public void submitMRJob(String jarFile, String input, String output) throws IOException{
        Job job = Job.getInstance(conf, "mr job.");
        job.setJar(jarFile);
        //job.setJarByClass(QuasiMonteCarlo.class);
        //job.setMapperClass(QmcMapper.class);
        //job.setReducerClass(QmcReducer.class);

        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReducer.class);
//        System.out.println("set input/output dir:");
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        try{
            System.out.println("Start mr job ...");
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void authentication(Configuration conf){
        System.out.println("Authentication with KDC.");
        UserGroupInformation.setConfiguration(conf);
        try{
            UserGroupInformation.getLoginUser().reloginFromTicketCache();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
