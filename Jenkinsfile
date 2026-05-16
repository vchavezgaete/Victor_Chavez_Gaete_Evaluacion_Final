pipeline {
    agent any

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B -ntp clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn -B -ntp test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Integration Tests') {
            steps {
                sh 'mvn -B -ntp verify'
            }
            post {
                always {
                    junit testResults: 'target/failsafe-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Publish Results') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar,target/surefire-reports/**,target/failsafe-reports/**',
                        fingerprint: true,
                        allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Pipeline OK'
        }
        failure {
            echo 'Pipeline fallido'
        }
        always {
            echo 'Fin de ejecución'
        }
    }
}
