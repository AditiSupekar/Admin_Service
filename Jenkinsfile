node {
 
    try {
        //notifySlack()
        stage('Checkout Code From GIT') {
            checkout scm
        }
         
        stage('Build Project (Static Code Analysis, Compile, JUnit, Code Coverage)') {
                withSonarQubeEnv('AARP SonarQube Server') {
                def M2_Home = tool name: 'maven_3_6_0', type: 'maven'
                sh "${M2_Home}/bin/mvn clean surefire-report:report jacoco:prepare-agent package jacoco:report sonar:sonar"
            }
        }
         
        stage("Wait for Sonar Quality Gate Response"){
            timeout(time: 1, unit: 'HOURS') {
              def qualityGate = waitForQualityGate()
              if (qualityGate.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
              }
            }
        }
         
        stage('Build Docker Image') {
                sh 'docker build -t idexceldemo/aarp-account-service:${BUILD_NUMBER}  -f Dockerfile .'
                sh 'docker tag idexceldemo/aarp-account-service:${BUILD_NUMBER} idexceldemo/aarp-account-service:latest'
        }
             
        stage('Push Docker Image to Docker Hub') {
            sh 'docker login -u <username> -p <password>'
            sh 'docker push idexceldemo/aarp-account-service'
        }
         
        stage('Deploy in AWS ECS'){
            sh 'aws ecs update-service --region us-east-1 --cluster idexceldemo-aarp --service aarp-account-service --force-new-deployment'
            sh 'sleep 30'
            sh 'aws ecs wait services-stable --region us-east-1 --cluster idexceldemo-aarp --service aarp-account-service'
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        throw e
    } finally {
            //sh 'aws s3 cp target/site/surefire-report.html s3://aarp-idexceldemo-reports/ --acl public-read'
        //notifySlack(currentBuild.result)
    }
     
}
 
def notifySlack(String buildStatus = 'STARTED') {
    // Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'
 
    def color
 
    if (buildStatus == 'STARTED') {
        color = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }
 
    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
 
    slackSend(color: color, message: msg)
}
