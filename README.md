# MultiDBs-KeywordSearchG-Group2-Server
Welcome to NeoSearch An Amazing Search Engine with Neo4j. 
If you wanna know how much is a 2007 Ford Focus. Don't go to GeySearch.
They will tell you nothing!
#ABOUT
Neo Search is a search engine based on the graph database—Neo4j. An API is used to get the keywords of clients and then search the results from all registered stores.
Find our project from: http://52.0.74.69/ 
#DETAILEDDESCRIPTION
The data can be gotten from the MetaStore. Tuples can be transmitted in the format of JSON.
Our first task is to deal with the data we got and store them in our database.
Secondly, we can search the keywords got from users in our database and return the result also in JSON format.
Finally, the result will be presented as a table to the users.
 #Step1 store
 After gotten the data, we need to analyze it for the format of it is JSON. This means each tuple will have a key and a value, and we store the key and value in the node in our database. Every JSON object will be stored as a node.
 #Step2 search
 There are two difficulties in the step. First we need to search the keyword while the property is unknown of the node. Second, it is hard to deal with multiple key words at a time.
 To deal with the first problem, we store the properties in a file and store the file in the memory. Each time we read the file in the memory, so that a continuous search can be achieve. As to the second problem, we deal with multiple key words as a string and split the string by space to get every key word.
 #Step 3 return
 The result will be returned to the front end in JSON format, just like the first step. Then we need to analyze the JSON format data and present it as a table to the user.
#HOW TO INSTALL
1. Login in AWS by SSH; install all the software (Maven, Git, JDK and nginx)
2. Go to the source folder and clone the project from GitHub sudo apt-get install maven 
sudo apt-get install git 
sudo apt-get install openjdk-7-jdk 
sudo apt-get install nginx
3.Set the default folder for html:
Cd /usr/share/nginx
Sudo rm –R html
Sudo ln –sv /opt/project/MutiDBs-KeywordSearchG-WebCleint2 html
4. Use maven install to build the project(on the source folder):
Sudo mvn install
5. Start the Service
6. Open the website

#API DOCUMENTATION
What it does: REST API is a method to connect back end and front end, which can transmit JSON formatted data to a certain URL.Input params: Keywords 
Path: NeoSearch/{keywords}
Out params: JSON formatted data
For example:
{"Gender":"Male","name":"Napoleon","School":"IS"} Method GET: a method used to retrieve and read a representation of a resource. We get the keywords from webpage and send back the result in JSON format.

