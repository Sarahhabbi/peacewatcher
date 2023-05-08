# peacewatcher

### Setup local Kafka stream

```
# Start the ZooKeeper service
$ bin/zookeeper-server-start.sh config/zookeeper.properties

# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties

# Read report topic
$ bin/kafka-console-consumer.sh --topic reports --from-beginning --bootstrap-server localhost:9092

```

## Run the app
```
// se placer à la racine du projet
sbt run 
```