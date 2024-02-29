import java.text.SimpleDateFormat

def defDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def defDate = new Date()
def defTimestamp = defDateFormat.format(defDate).toString()

def secrets = [
  [path: 'PT_Demo_Automation/carsModel', engineVersion: 2, secretValues: [
	    [envVar: 'v_SaucelabsUser', vaultKey: 'v_SaucelabsUser'],
	    [envVar: 'v_SaucelabsAccessKey', vaultKey: 'v_SaucelabsAccessKey']
 	]
  ]
]

def configuration = [vaultUrl: 'http://localhost:8200',  vaultCredentialId: 'VaultCredential', engineVersion: 2]


pipeline {

    agent any
    
    tools {
        maven 'M3'
        jdk 'jdk8.221'
    }
    
    options {
		buildDiscarder(logRotator(numToKeepStr: '20'))
	    disableConcurrentBuilds()
	}
    
    stages {
	
        stage ('Build') {
            steps {
                sh ("mvn -X clean verify")
            }
        }
        
		stage("Ejecutar Pruebas") {
            steps {
            	withVault([configuration: configuration, vaultSecrets: secrets]) {
        			script {
	        			try {
	        				sauce('saucelabs-Web-US') {
	    						sauceconnect(useGeneratedTunnelIdentifier: true, verboseLogging: true) {
			        				sh ("mvn test -Denvironment=${ENTORNO_EJECUCION} -Dcucumber.features=src/test/resources/features/ -Dcucumber.filter.tags=${ESCENARIO} -Dcucumber.plugin=json:target/site/result.json -Dcucumber.glue=demo")
			        			}
			        		}
	        			}
	        			catch (ex) {
	        				echo 'Finalizo ejecucion con fallos...'
	        				error ('Failed')
	                    }
					}
                }
            }
        }
        
        stage ('Reporte') {
        	steps {
        		script {
					try {
                     	sh "mvn serenity:aggregate"
                    	publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}/target/site/serenity", reportFiles: 'index.html', reportName: 'Evidencias de Prueba', reportTitles: 'Reporte de Pruebas'])
                        saucePublisher()
                        echo 'Reporte realizado con exito'
                    }

                    catch (ex) {
                        echo 'Reporte realizado con Fallos'
                        error ('Failed')
                    }
                }
            }
        }
    }
}