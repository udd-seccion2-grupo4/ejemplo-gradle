pipeline {
    agent any
    parameters {
        choice choices: ['gradle', 'maven'], description: 'indicar la herramienta de construcción', name: 'buildTool'
    }
    stages {
        stage('pipeline') {
            steps {
                script {
                    if (buildTool != null) {
                        def buildToolGroovy = buildTool + '.groovy'
                        def ejecucion = load buildToolGroovy
                        ejecucion.call()
                    }
                }
            }
        }
    }
    post {
        success {
            slackSend(message: '[gamboa][' + env.JOB_NAME + '][' + buildTool + '] Ejecución Exitosa.')
        }
        failure {
            slackSend(message: '[gamboa][' + env.JOB_NAME + '][' + buildTool + '] Ejecución Fallida en Stage [' + env.STAGE_NAME + '].')
        }
    }
}
