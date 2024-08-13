package com.ms.user.producer;

import com.ms.user.dto.jms.JmsEmailsDto;

public interface IMsEmailProducer {
    void generateTransactionEmail(JmsEmailsDto jmsEmailDto);
    //Interface para crear servicio de mails
}
