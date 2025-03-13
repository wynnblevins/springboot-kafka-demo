# About This Project
This project demonstrates how to work with Kafka topics in Java.  It is a multi module Java project which consists of a Kafka producer that reads a stream of Wikimedia change data then writes (ie produces) that data onto a Kafka topic.  In addition, there is a corresponding consumer which reads (ie consumes) that data from the Kafka topic and writes it to a database.

## Prerequisites
In order to run this project, you will need several things installed on your local machine.  First, it is assumed that you have both Maven and Java installed and added to your path.  Second, you will need a MySQL database called `wikimedia` set up locally and accepting connections on port `3306`.  Finally, you will also need to have a local Kafka server installed and running on port `9092` with a running instance of Zookeeper.  In order to start Zookeeper, navigate into your Kafka installation directory and run the following command:
```sh
bin/zookeeper-server-start.sh config/zookeeper.properties
```
Next, start the Kafka server by running:
```sh
bin/kafka-server-start.sh config/server.properties
```

## How to Run
The `kafka-consumer-database` project communicates with a local MySQL database running on port `3306`.  In order to establish the consumer's database connection, you must first set the database username and password in the `kafka-consumer-database` project's `application.properties` file.  After the database credentials are set within the `application.properties` file, navigate into the project's `kafka-consumer-database` directory and run the following command to start the Kafka consumer:
```sh
mvn clean spring-boot:run
```
Once the consumer is running, you should start producing to the `wikimedia_recentchange` topic.  In order to begin producing to the `wikimedia_recentchange` topic, run the following within the `kafka-producer-wikimedia` directory: 
```sh
mvn clean spring-boot:run
```

## How to Verify Project Is Working
You can verify that the project is working as expected by running the following command, which checks the contents of the wikimedia_recentchange topic:
```sh
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic wikimedia_recentchange
```
You may also choose to check the contents of the `wikimedia` database's `wikimedia` table to view the change stream data.