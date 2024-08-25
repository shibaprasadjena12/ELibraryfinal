#ELibrary
To run this project in linux <br>
#Prerequisites
JDK, Tomcat 9, Maven should be installed <br>
git clone https://github.com/shibaprasadjena12/ELibraryfinal.git <br>
cd ELibraryfinal/ <br>
mvn clean package <br>
mv target/ELibraryfinal2-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ <br>
cd /opt/tomcat/bin <br>
./startup.sh
