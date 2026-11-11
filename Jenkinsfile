
pipeline {
    agent any

    stages {
        stage('GitHub Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Backend Maven Build') {
            steps {
                bat 'call backend\\mvnw.cmd -f backend\\pom.xml clean package -DskipTests'
            }
        }

        stage('Backend Docker Build') {
            steps {
                bat '"C:\\Users\\Admin\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t smart-incident-management-backend:latest -f backend\\Dockerfile backend'
            }
        }

        stage('Frontend Docker Build') {
            steps {
                bat '"C:\\Users\\Admin\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" build -t smart-incident-management-frontend:latest -f frontend\\Dockerfile frontend'
            }
        }
    }
}
