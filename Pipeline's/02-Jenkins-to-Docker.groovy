pipeline {
    agent any

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
                    bat 'docker build -t orsproject04:latest .'
                }
            }
        }

        stage('Verify Docker Image') {
            steps {
                bat 'docker images orsproject04'
            }
        }
    }
}