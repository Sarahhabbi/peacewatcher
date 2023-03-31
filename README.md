# peacewatcher

### Setup local Kafka stream

```
# Start the ZooKeeper service
$ bin/zookeeper-server-start.sh config/zookeeper.properties

# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties

# Create report topic
$ bin/kafka-topics.sh --create --topic reports --bootstrap-server localhost:9092

# Read report topic
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092

```

## Run the app
```
cd src/main/scala/app
sbt run 
```