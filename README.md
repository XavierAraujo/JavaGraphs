# Project

This project contains an implementation of a Java graph library.

# Dependencies

To compile the project is required to install [Maven](https://maven.apache.org/) and [JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

# Compilation

The project can be compiled using Maven. You can compile the project executing the following command:

```
mvn clean compile assembly:single
```

After compiling you can execute the project executing the following command:

```
java -jar target/JavaGraphs-jar-with-dependencies.jar
```

If you want to run the unit tests you can execute the following command:

```
mvn test
```