#stop service
lsof -i:7654
kill -9 $(ps aux | grep java | grep multidbskeywordsearchgserverapi-0.1-SNAPSHOT.jar | awk '{print $2}')