pipeline {
    agent any

    stages {
        stage('pipeline') {
            steps {
                script {
                    def ejecucion = load 'gradle.groovy'
                    ejecucion.call()
                }
                script {
                    def ejecucion = load 'maven.groovy'
                    ejecucion.call()
                }
            }
        }
    }
}
