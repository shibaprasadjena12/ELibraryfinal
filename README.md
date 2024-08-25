<h1>ELibrary</h1>
To run this project in linux <be>
<h3>Prerequisites</h3>
JDK, Tomcat 9, Maven should be installed <br>
  <hr>
git clone https://github.com/shibaprasadjena12/ELibraryfinal.git <br>
cd ELibraryfinal/ <br>
mvn clean package <br>
mv target/ELibraryfinal2-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/ <br>
cd /opt/tomcat/bin <br>
./startup.sh
