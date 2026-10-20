pipeline {
    agent any

    environment {
        AWS_HOST = '54.87.174.177'
        AWS_USER = 'ubuntu'
        DOCKER_IMAGE = 'lakhanporwal/orsproject04:latest'
    }

    stages {
        stage('Deploy on AWS EC2') {
            steps {
                withCredentials([
                    sshUserPrivateKey(
                        credentialsId: 'aws-ec2-ssh',
                        keyFileVariable: 'SSH_KEY',
                        usernameVariable: 'SSH_USER'
                    )
                ]) {
                    bat '''
                        @echo off
                        ssh -i "%SSH_KEY%" -o StrictHostKeyChecking=no %SSH_USER%@%AWS_HOST% "sudo docker pull %DOCKER_IMAGE% && sudo docker start mysql-container && sudo docker rm -f ors-container && sudo docker run -d --restart unless-stopped --name ors-container --network ors-network -p 8080:8080 %DOCKER_IMAGE%"
                        if errorlevel 1 exit /b 1
                    '''
                }
            }
        }
    }
}