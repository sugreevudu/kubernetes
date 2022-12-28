pipeline {
    
    agent {
            
       label 'kubernetes'
	        
          }

        stages {

            stage('git checkout') {
	
                steps {

	            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sugreevudu/kubernetes.git']]])			                   }
							                           }
								        }
								          
  stage('mysql deployment') {
							       steps {
								                    sh 'kubectl create -f mysql-user-pass.yaml'

	            sh 'kubectl create -f mysql-db-url.yaml'
                    sh 'kubectl create -f mysql-root-pass.yaml'
                    sh 'kubectl create -f mysql-pv.yaml'
                    sh 'kubectl create -f mysql-pvc.yaml'
                    sh 'kubectl create -f mysql-deployment.yaml'
                    sh 'kubectl create -f mysql-service.yaml'		         
	     }
									         }
									    }
										}

