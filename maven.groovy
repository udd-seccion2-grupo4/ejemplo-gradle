
def call() {
    stage('build & unit test') {
        stageName = 'build & unit test'
        sh './mvnw clean compile -e'
        sh './mvnw clean test -e'
        sh './mvnw clean package -e'
    }
    stage('sonar') {
        stageName = 'sonar'
        def scannerHome = tool 'sonar-scanner'
        withSonarQubeEnv('docker-compose-sonarqube') {
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.sources=src -Dsonar.java.binaries=build"
        }
    }
    stage('run') {
        stageName = 'run'
        sh './mvnw spring-boot:run &'
        sleep 20
    }
    stage('test run') {
        stageName = 'test run'
        sh 'curl -X GET http://localhost:8081/rest/mscovid/test?msg=testing'
    }
    stage('nexus') {
        stageName = 'nexus'
        nexusPublisher nexusInstanceId: 'nexus3-docker',
        nexusRepositoryId: 'ejemplo-maven',
        packages: [
            [
                $class: 'MavenPackage',
                mavenAssetList: [
                    [classifier: '', extension: '', filePath: 'build/DevOpsUsach2020-0.0.1.jar']
                ],
                mavenCoordinate: [
                    artifactId: 'DevOpsUsach2020',
                    groupId: 'com.devopsusach2020',
                    packaging: 'jar',
                    version: '0.0.1'
                ]
            ]
        ]
    }
}

return this
