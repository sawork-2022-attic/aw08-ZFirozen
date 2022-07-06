package com.micropos.delivery;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryConfig {
    private static final boolean NON_DURABLE = false;
    public final static String QUEUE_NAME = "default.queue";
    public final static String EXCHANGE_NAME = "default.exchange";

    @Bean
    public Declarables defaultBindings() {
        Queue queue = new Queue(QUEUE_NAME, NON_DURABLE);
        FanoutExchange exchange = new FanoutExchange(EXCHANGE_NAME, NON_DURABLE, false);

        return new Declarables(queue, exchange, BindingBuilder.bind(queue).to(exchange));
    }
}
