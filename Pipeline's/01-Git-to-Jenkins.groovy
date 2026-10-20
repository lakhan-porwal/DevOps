pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/lakhan-porwal/ORSProject04-Complete.git'
            }
        }
    }
}