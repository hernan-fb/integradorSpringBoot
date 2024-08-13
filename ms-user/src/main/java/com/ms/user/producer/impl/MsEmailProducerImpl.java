package com.ms.user.producer.impl;

import com.ms.user.dto.jms.JmsEmailsDto;
import com.ms.user.producer.IMsEmailProducer;
import com.ms.user.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component // TODO modificar a @Service (service extiende de component)
public class MsEmailProducerImpl implements IMsEmailProducer {
    private final JmsTemplate jmsTemplate;

    public MsEmailProducerImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void generateTransactionEmail(JmsEmailsDto jmsEmailsDto) {
        try {
            final String messageBody = JsonUtil.convertToJson(jmsEmailsDto);

            // true = que va a ir a un tópico (patrón de diseño publicador / suscriptor)
            // false = que va a ir a una cola de mensajería queue (está escuchando)

            jmsTemplate.setPubSubDomain(false);
            assert messageBody != null;
            jmsTemplate.convertAndSend(
                    "queue.msemail.generate.email", //nombre del tópico que vamos a crear en activemq
                    messageBody
            );
        } catch (JmsException e) {
            log.error(e.getMessage(), e);
        }
    }
}
