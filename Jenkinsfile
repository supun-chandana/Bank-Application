pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'schandana/banking-app:latest'
        DOCKER_REGISTRY_CREDENTIALS = 'my-dockerhub-id'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Cloning repository..."
                git branch: 'main', url: 'https://github.com/supun-chandana/Bank-Application.git'
            }
        }

        stage('Build with Maven') {
            steps {
                echo "Building the application with Maven..."
                sh 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                echo "Running unit tests..."
                sh 'mvn test'
            }
        }

        stage('Dockerize Application') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push Docker Image to Registry') {
            steps {
                echo "Pushing Docker image to Docker Hub..."
                withCredentials([usernamePassword(credentialsId: 'my-docker-id', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }

        stage('Deploy Locally') {
            steps {
                echo "Deploying Docker container locally..."
                sh '''
                    docker stop banking-app || true
                    docker rm banking-app || true
                    docker run -d --name banking-app -p 8081:8080 $DOCKER_IMAGE
                '''
            }
        }

        stage('Post-Deployment Verification') {
            steps {
                echo "Verifying deployment..."
                script {
                    sleep(time: 30, unit: 'SECONDS')
                    def response = sh(script: "curl -s -o /dev/null -w '%{http_code}' http://localhost:8081/health", returnStdout: true).trim()
                    if (response != '200') {
                        error "Deployment verification failed!"
                    } else {
                        echo "Deployment verification succeeded!"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check the logs for details."
        }
    }
}
