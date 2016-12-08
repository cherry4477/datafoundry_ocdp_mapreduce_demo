# datafoundry_ocdp_mapreduce_demo

A simple example for submit mapreduce job.

- Clone code from github
   ```
   git clone https://github.com/asiainfoLDP/datafoundry_ocdp_mapreduce_demo.git
   ```
- Copy MapReduce jar file to jars/ directory

- Edit scripts/start.sh file:
  > replace BSI_MAPREDUCE_PRINCIPAL and BSI_MAPREDUCE_PASSWORD to tenant's principal and password

  > replace MAPREDUCE_JAR_FILE to mapreduce jar file name, and replace ARG1, ARG2 ... to real program args

- Build docker image

- Run Docker container