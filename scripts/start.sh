#!bin/bash
echo "36.110.131.65 hadoop-1.jcloud.local" >> /etc/hosts
echo "36.110.132.55 hadoop-2.jcloud.local" >> /etc/hosts

echo "kinit"
date
kinit <Mapreduce tenant krb principal> <<!!
<Mapreduce tenant krb password>
!!

yarn jar /root/jars/<mapreduce jar file> <arg1> <arg2> ...
