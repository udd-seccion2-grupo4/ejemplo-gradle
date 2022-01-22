pipeline {
    agent any
    parameters {
        choice choices: ['gradle', 'maven'], description: 'indicar la herramienta de construcción', name: 'buildTool'
    }
    stages {
        stage('pipeline') {
            steps {
                script {
                    def buildToolGroovy = buildTool + '.groovy'
                    def ejecucion = load buildToolGroovy
                    ejecucion.call()
                }
            }
        }
    }
}
