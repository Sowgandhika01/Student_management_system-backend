# Student_management_system-backend

http://localhost:8083/swagger-ui/index.html

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
C:\kafka\kafka\bin\windows\kafka-topics.bat ^
 --create ^
 --topic hello-topic ^
 --bootstrap-server localhost:9092 ^
 --partitions 1 ^
 --replication-factor 1 For creating kafka topic 


 ---for the running purpose kafka---
 C:\kafka\kafka\bin\windows\kafka-server-start.bat C:\kafka\kafka\config\server.properties 
 ---Knowing about kafka is working or not---
 C:\kafka\kafka\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list

Getters → Read values
Setters → Modify values
Gettes and setters are mainly used to access and modify private data safely.
Constructor → Initialize object

--If kafka shows error already running port default like that run these commands--
 C:\kafka\kafka\bin\windows\kafka-storage.bat random-uuid

 C:\kafka\kafka\bin\windows\kafka-storage.bat format --standalone -t vxNw0_HDR3GqB3s5TMPPNA -c C:\kafka\kafka\config\server.properties

 .\bin\windows\kafka-server-start.bat .\config\server.properties
 

--- If I have to place the OrderConsumer in the consumer file this is the code ---
package com.cscorner.helloapp.kafka.consumer;

import com.cscorner.helloapp.dto.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerListener {

    @KafkaListener(topics =  multi-order-topic, groupId = multi-order-group)
    public void consume(OrderDTO order) {
        System.out.println( Order Received:);
        System.out.println(ID:  + order.getId());
        System.out.println( Product:  + order.getProduct());
        System.out.println( Price:  + order.getPrice());
    }
}

