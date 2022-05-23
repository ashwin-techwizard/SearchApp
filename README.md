# SearchApp
The `application` is created using scala version `2.13.3` and gradel is used as a build tool
`./gradlew build` will build the jars in the `/build/libs/SearchApp-1.0-SNAPSHOT.jar` 

##Usage
* Once the jar is careted using gradel build 
* Provided scala 2.13 is installed in your system
* The Application can be triggered using below command
  
   `scala -classpath ./build/libs/SearchApp-1.0-SNAPSHOT.jar com.SearchApp ./src/main/resources/TextDir`
* Any folder path can be provided which consist of text files
* `:quit` can be used to end the application
##Working Example

(base) admin@admins-MacBook-Pro SearchApp % scala -classpath ./build/libs/SearchApp-1.0-SNAPSHOT.jar com.SearchApp ./src/main/resources/TextDir


3 files indexed
search >

sigma guru pluto

file2 : 100%

file1 : 66%

file3 : 33%

search >

:quit