
pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'chethan123444'
        BACKEND_IMAGE = 'chethan123444/smart-incident-management-backend:latest'
        FRONTEND_IMAGE = 'chethan123444/smart-incident-management-frontend:latest'
        DOCKER = 'C:\\Users\\Admin\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe'
    }

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
                bat '"%DOCKER%" build -t smart-incident-management-backend:latest -f backend\\Dockerfile backend'
            }
        }

        stage('Frontend Docker Build') {
            steps {
                bat '"%DOCKER%" build -t smart-incident-management-frontend:latest -f frontend\\Dockerfile frontend'
            }
        }

        stage('Docker Hub Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_TOKEN'
                )]) {
                    script {
                        try {
                            bat 'powershell -NoProfile -NonInteractive -Command "$env:DOCKER_TOKEN | & $env:DOCKER login -u $env:DOCKER_USER --password-stdin"'
                            bat '"%DOCKER%" tag smart-incident-management-backend:latest %DOCKER_USER%/smart-incident-management-backend:latest'
                            bat '"%DOCKER%" tag smart-incident-management-frontend:latest %DOCKER_USER%/smart-incident-management-frontend:latest'
                            bat '"%DOCKER%" push %DOCKER_USER%/smart-incident-management-backend:latest'
                            bat '"%DOCKER%" push %DOCKER_USER%/smart-incident-management-frontend:latest'
                        } finally {
                            bat '"%DOCKER%" logout'
                        }
                    }
                }
            }
        }

        stage('Deploy Backend and Frontend') {
            steps {
                withCredentials([file(credentialsId: 'smartims-env', variable: 'ENV_FILE')]) {
                    script {
                        try {
                            bat 'copy /Y "%ENV_FILE%" ".env.properties" >NUL'
                            bat '"C:\\Users\\Admin\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker-compose.exe" -p smart-incident-management up -d --no-deps --no-build backend frontend'
                        } finally {
                            bat 'if exist ".env.properties" del /F /Q ".env.properties"'
                        }
                    }
                }
            }
        }
    }
}
