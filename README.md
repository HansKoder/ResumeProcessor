# Branch loop-vs-streams-parallel

# Inject CV to test

1) mkdir cvs in the project's root
2) You need to copy each resume from cvs-original to cvs
3) execute PopulateCV only once, the parameter 249 is because you must have the original cv.
4) You can update the parameters numbers of copies


# Execute main to anaylize of difference among loop, stream and parallel using benchmark
1) command maven
```mvn
mvn clean install
```

2) ejecute benchmark

```java
java -jar target/ResumeProcessor-1.0-SNAPSHOT.jar
```

# Execute main to anaylize of difference among loop, stream and parallel using main.js
1) Run main.js using intellij IDE 
