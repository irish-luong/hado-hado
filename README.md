# hado-hado

## What about me
I love the meaning of `Man search for meaning`. 

This project we build to learning how to find out the meaning from data 
- Note my life experience
- Set up data pipeline
- Deploy application to connect to data pipeline to mine insight from them
- Learn how to protect data

### Contact
- Telegram: @duclmq
- LindkedIn: linkedin.com/in/max-luong
- Email: lmquanduc.iser@gmail.com || duc.luong2@tiki.vn


## Scala - Spark App with sbt
### sbt
[sbt](https://www.scala-sbt.org/) is a build tool which is created for Scala project and supported by [Lighbend](https://www.lightbend.com/)

1. Install sbt with HomeBrew
   ```
   $ brew install sbt
   ```
2. Start Scala project: [scala-build-tool-sbt](https://docs.scala-lang.org/overviews/scala-book/scala-build-tool-sbt.html)


## Kafka
### Knowledge
1. Set up kafka: [Medium](https://medium.com/better-programming/your-local-event-driven-environment-using-dockerised-kafka-cluster-6e84af09cd95)
2. Full-stack implementation: [Confluentinc](https://github.com/confluentinc/examples/blob/5.4.0-post/cp-all-in-one/docker-compose.yml)
### Practice
1. Start broker
   ```
   $ cd kafka-broker
   $ docker-compose up -d
   $ docker exec -ti kafka-tools bash
   ```
2. Create kafka topic
   ```
   $ kafka-topics \
        --create \
        --bootstrap-server localhost:9092 \
        --replication-factor 1 \
        --partitions 2 \
        --topic to-do-list
   ```
3. List kafka topic
    ```
    $ kafka-topics \
        --list \
        --bootstrap-server localhost:9092
    ```
4. Produce message to the kafka topic
   ```
    $ kafka-console-producer \
        --broker-list localhost:9092 \
        --topic to-do-list \
        --property "parse.key=true" \
        --property "key.separator=:"
   ```
5. Consume message from the kafka topic
   ```
    $ kafka-console-consumer \
        --bootstrap-server localhost:9092 \
        --from-beginning \
        --topic to-do-list \
        --property "print.key=true"
   ```
