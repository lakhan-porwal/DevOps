ORS JENKINS PIPELINE CODES — SEQUENCE
Each TXT file contains the code for the pipeline named in the filename.

1. Jenkins-Connect-Aws-Checking — SSH connection test
2. AWS-Docker-Install — install/check Docker on AWS
3. AWS-Docker-Network — create/check ors-network
4. AWS-MySQL-Setup — setup/check MySQL and database p04
5. ORS-DockerHub-Push — push Webapp and MySQL images to Docker Hub
6. AWS-Pull-DockerHub-Images — pull both images on AWS
7. AWS-ORS-Full-Deployment — deploy Webapp and verify containers/logs
8. AWS-Running-Check — list AWS containers
9. ORS-Git-DockerDesktop — local Windows build and Docker Compose

Current settings discussed:
AWS host: 3.87.152.16
SSH key: C:/Users/Mohit porwal/Downloads/ORSProject04-key.pem
Docker network: ors-network
MySQL container: mysql-container
MySQL volume: mysql-data
Database: p04
MySQL external port: 3308 -> internal port 3306
Webapp container: webapp-container
Webapp port: 8080 -> 8080
Docker Hub images: lakhanporwal/webapp:latest and lakhanporwal/mysql:latest
Internal Webapp JDBC URL: jdbc:mysql://mysql:3306/p04
Phone URL: http://3.87.152.16:8080/ORSProject04/

Some historical scripts have been reconstructed from the conversation summary because their exact original code was not all available. Review those files before replacing a working job.

Security: do not share the PEM key or passwords. Restrict inbound TCP 3308 to your own public IP; do not expose MySQL to 0.0.0.0/0.
Important: Workbench uses port 3308; Webapp-to-MySQL uses hostname mysql and internal port 3306.
