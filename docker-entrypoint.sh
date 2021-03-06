#!/bin/bash
set -e

apt-get update

echo "creating folder for servers source code"

cd /opt
mkdir -p project
cd project

echo "cloning two git repos"

git clone https://github.com/infsci2711/MultiDBs-KeywordSearchG-Server2.git
git clone https://github.com/infsci2711/MultiDBs-Utils.git

echo "building utils project"

cd /opt/project/MultiDBs-Utils
mvn install

echo "building server project"

cd /opt/project/MultiDBs-KeywordSearchG-Server2
mvn install

echo "creating folder for deployed code"

cd /opt/project
mkdir -p deployed

echo "copying jar and config file to deploed folder"

cp /opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI/target/multidbskeywordsearchgserverapi-0.1-SNAPSHOT.jar /opt/project/deployed
cp /opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI/src/main/resources/config.properties /opt/project/deployed
cp /opt/project/MultiDBs-KeywordSearchG-Server2/property.csv /opt/project/deployed

echo "starting java server"

cd /opt/project/deployed
nohup java -jar  /opt/project/deployed/multidbskeywordsearchgserverapi-0.1-SNAPSHOT.jar /opt/project/deployed/config.properties > /opt/project/deployed/log.out 2> /opt/project/deployed/error.log < /dev/null &

exec "$@"
