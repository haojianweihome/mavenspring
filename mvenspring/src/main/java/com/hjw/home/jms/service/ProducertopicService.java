package com.hjw.home.jms.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
@Service
public class ProducertopicService {
	@Autowired
	private JmsTemplate jmstopicTemplate;  
    
   /* public void sendMessage(Destination destination, final String message) {  
        System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(message);  
            }  
        });
    } */  
	public void sendMessage(Destination destination, final Object obj) {  
		jmstopicTemplate.convertAndSend(destination, obj);
    }
}