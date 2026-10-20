pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'orsproject04'
        DOCKERHUB_IMAGE = 'lakhanporwal/orsproject04:latest'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/lakhan-porwal/ORSProject04-Complete.git'
            }
        }

        stage('Build WAR') {
            steps {
                dir('ORSProject04') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('ORSProject04') {
                    bat 'docker build -t %DOCKER_IMAGE%:latest .'
                }
            }
        }

        stage('Tag Docker Image') {
            steps {
                bat 'docker tag %DOCKER_IMAGE%:latest %DOCKERHUB_IMAGE%'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'docker-creds-new',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    bat '''
                        @echo off
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        if errorlevel 1 exit /b 1

                        docker push %DOCKERHUB_IMAGE%
                        if errorlevel 1 exit /b 1

                        docker logout
                    '''
                }
            }
        }
    }
}