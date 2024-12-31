pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/supun-chandana/Bank-Application.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Build the project using Maven
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run the tests
                    sh 'mvn test'
                }
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    // Build Docker image
                    sh 'docker build -t bank-application .'
                }
            }
        }
        stage('Deploy to Docker Hub') {
            steps {
                script {
                    // Push Docker image to Docker Hub
                    sh 'docker push schandana/bank-application:v1'
                }
            }
        }
    }
}
