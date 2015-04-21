#Start service
cd /opt/project/MultiDBs-KeywordSearchG-Server2/MultiDBsKeywordSearchGServerAPI

nohup java -jar target/multidbskeywordsearchgserverapi-0.1-SNAPSHOT.jar src/main/resources/config.properties> log.out 2> error.log < /dev/null &