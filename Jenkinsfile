
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
        bat 'call "C:\\Users\\Admin\\.m2\\wrapper\\dists\\apache-maven-3.9.12\\59fe215c0ad6947fea90184bf7add084544567b927287592651fda3782e0e798\\bin\\mvn.cmd" -f backend\\pom.xml clean package -DskipTests'
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
