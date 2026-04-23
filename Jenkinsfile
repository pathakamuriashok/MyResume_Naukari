pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Automation') {
            steps {
                bat 'mvn exec:java -Dexec.mainClass="ResumeUpdate.Naukari2"'
            }
        }
    }
}
