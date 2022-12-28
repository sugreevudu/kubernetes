pipeline {
    
    agent {
            
       label 'kubernetes'
	        
          }

        stages {

            stage('git checkout') {
	
                steps {

	            checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sugreevudu/kubernetes.git']]])			                   			                           
                      }
				   }
								          
            stage('mysql deployment') {
			  steps {
			    sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-user-pass.yaml'
	            sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-db-url.yaml'
                sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-root-pass.yaml'
                sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-pv.yaml'
                sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-pvc.yaml'
                sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-deployment.yaml'
                sh 'kubectl create -f wordpre-phpmysql-mysql-deployments/mysql-service.yaml'		         
	                 }
			    }
          }
	}