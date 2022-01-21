pipeline {
    agent any

    stages {
        stage('build & unit test') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }
        stage('sonar') {
            steps {
                script {
                    def scannerHome = tool 'sonar-scanner'
                    withSonarQubeEnv('docker-compose-sonarqube') {
                      sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.sources=src -Dsonar.java.binaries=build"
                    }
                }
            }
        }
        stage('nexus') {
            steps {
                script {
                   echo 'nexus'
                }
            }
        }
        stage('run') {
            steps {
                script {
                    sh './gradlew bootRun &'
                    sleep 20
                }
            }
        }
        stage('test') {
            steps {
                script {
                   sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
                }
            }
        }
    }
}
