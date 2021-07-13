pipeline {
  agent any
  stages {
    stage("检出") {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: GIT_BUILD_REF]],
          userRemoteConfigs: [[
            url: GIT_REPO_URL,
            credentialsId: CREDENTIALS_ID
        ]]])
      }
    }
    stage('自定义构建过程') {
      agent {
        docker {
          reuseNode 'true'
//           registryUrl 'https://coding-public-docker.pkg.coding.net'
          image 'maven:3.6.3-openjdk-11-slim'
        }
      }
      steps {

        sh "mvn clean test package"
        // 请在此处补充您的构建过程
      }
    }
  }
}