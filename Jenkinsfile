pipeline {
    agent any
    
    stages {
        stage('Get Code')
        {
            steps{
                bat 'dir'
                bat 'echo %WORKSPACE%'
            }
        }
        stage('Get Code') {
            steps {
                // Obtener código del repo
                // git branch: "master", url: 'https://github.com/anieto-unir/cucumber-helloworld.git'
				script {
					scmVars = checkout scm
					echo 'scm : the commit id is ' + scmVars.GIT_COMMIT
				}
            }
        }
        
        stage('Build&Test')
        {
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }
        
        stage('Results')
        {
            steps {
                cucumber fileIncludePattern: 'cucumber.json', jsonReportDirectory: 'target/', reportTitle: 'Cucumber Reports'
            }
        }
    }
}		