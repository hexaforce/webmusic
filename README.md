# webmusic
Hexaforce WebMusic

  cd /Users/relics9/git/webmusic
  mvn clean install -DskipTests=true
  scp -i ~/hexaforce.pem web/target/web-0.0.1-SNAPSHOT.jar ec2-user@13.230.163.159:
  scp -i ~/hexaforce.pem api/target/api-0.0.1-SNAPSHOT.jar ec2-user@13.230.163.159:
  ssh -i ~/hexaforce.pem ec2-user@13.230.163.159

nohup java -jar api-0.0.1-SNAPSHOT.jar &
nohup java -jar web-0.0.1-SNAPSHOT.jar &


