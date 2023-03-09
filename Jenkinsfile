pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package' // Build the Karate Framework
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test -Dtest=TestRunner' // Run the Karate test suite
      }
    }
  }
}
