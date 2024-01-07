pipeline {
  
  environment {
    dockerimagename = "glebkr/demo-spring"
  }
    
  agent any
    
  tools {
    maven 'maven'
  }
    
  stages {

    stage('Build Maven') {
      steps {
        checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/glebkr/demo']])
        bat 'mvn clean install'
      }
    }

    stage('Build docker image') {
      steps {
        script {
          bat "docker build -t ${dockerimagename} ."
        }
      }
    }

    stage('Push image to docker hub') {
      steps {
        script {
          withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
            bat "docker login -u glebkr -p ${dockerhubpwd}"
            bat "docker push ${dockerimagename}"
          }
        }
      }
    }

  }

}