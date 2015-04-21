#install
sudo apt-get install maven
sudo apt-get install mysql-server
sudo apt-get install nginx
sudo apt-get install openjdk-7-jdk

#folders and environments
cd /opt
sudo mkdir project
cd project

#clone project
sudo git clone https://github.com/infsci2711/MultiDBs-KeywordSearchG-WebCleint2.git
sudo git clone https://github.com/infsci2711/MultiDBs-KeywordSearchG-Server2.git
sudo git clone https://github.com/infsci2711/MultiDBs-Utils.git

#set client
cd /usr/share/nginx
sudo rm -R html
sudo ln -sv /opt/project/MultiDBs-KeywordSearchG-WebCleint2 html

#install maven
cd /opt/project/MultiDBs-Utils
mvn install
cd /opt/project/MultiDBs-KeywordSearchG-Server2
mvn install
cd /opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI
mvn install

#cd /opt
sudo chown -R student:student project
kill -9 $(ps aux | grep java | grep multidbskeywordsearchgserverapi-0.1-SNAPSHOT.jar | awk '{print $2}')
cd /opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI
