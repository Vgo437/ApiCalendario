
pipeline {
    agent any
    tools {
        maven 'Maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Compilar') {
            steps {
                dir('api') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Copiar JAR') {
            steps {
                sh 'cp api/presentacion/target/presentacion-0.0.1-SNAPSHOT.jar publicacion/'
            }
        }
        stage('Desplegar') {
            steps {
                sh 'docker compose down'
                sh 'docker compose up --build -d'
            }
        }
    }
    post {
        success {
            echo 'Despliegue exitoso!'
        }
        failure {
            echo 'El pipeline falló. Revisa los logs.'
        }
    }
}
EOF