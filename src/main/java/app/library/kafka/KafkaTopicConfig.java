package app.library.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    /*
    @Bean
    public NewTopic booklibrary(){
        return TopicBuilder.name("books").partitions(10).build();
    }*/
}
