import subprocess
import multiprocessing

mongoDB = subprocess.call("sudo service mongod start &", stdout= subprocess.PIPE, shell= True)  

zookeeper = subprocess.call(
	"sudo /home/nabegh/kafka/kafka_2.11-2.0.0//bin/zookeeper-server-start.sh /home/nabegh/kafka/kafka_2.11-2.0.0/config/zookeeper.properties &",
	 stdout= subprocess.PIPE, shell= True) 

kafka = subprocess.call(
	"sudo /home/nabegh/kafka/kafka_2.11-2.0.0//bin/kafka-server-start.sh /home/nabegh/kafka/kafka_2.11-2.0.0/config/server.properties &",
	 stdout= subprocess.PIPE, shell= True)

#gemfire = subprocess.call(
#	"java -jar Learn/EMC_Mentorship_program_project/IoT-GemFire/target/IoT-GemFire-0.0.1-SNAPSHOT.jar &",
#	stdout= subprocess.PIPE, shell= True)

#transformer = subprocess.call(
#	"java -jar Learn/EMC_Mentorship_program_project/IoT-EnrichmentTransformer/target/IoT-EnrichmentTransformer-0.0.1-SNAPSHOT.jar  &",
#	stdout= subprocess.PIPE, shell= True)

 #Set up the parallel task pool to use all available processors
#simulator = subprocess.call(
#	"java -jar Learn/EMC_Mentorship_program_project/IoT-CarSimulator/IoT-CarSimulator/target/IoT-CarSimulator-0.0.1-SNAPSHOT.jar  &",
#	stdout= subprocess.PIPE, shell= True)

#print('The End')

