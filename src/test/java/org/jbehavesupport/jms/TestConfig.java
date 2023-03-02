package org.jbehavesupport.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.jbehavesupport.core.report.XmlReporterFactory;
import org.jbehavesupport.core.support.YamlPropertySourceFactory;
import org.jbehavesupport.jms.report.extension.JmsXmlReporterExtension;
import org.jbehavesupport.jms.test.oxm.NameRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@PropertySource(value = "test.yml", factory = YamlPropertySourceFactory.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = org.jbehavesupport.core.jms.JmsSteps.class))
public class TestConfig {

    @Bean
    public XmlReporterFactory xmlReporterFactory() {
        return new XmlReporterFactory();
    }

    @Bean
    public JmsXmlReporterExtension jmsXmlReporterExtension() {
        return new JmsXmlReporterExtension();
    }

    @Bean
    ConnectionFactory connectionFactory(@Value("${jms.brokerUrl}") String brokerUrl) {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean
    @Qualifier("TEST")
    public JmsJaxbHandler jmsJaxbHandler(ConnectionFactory connectionFactory) {
        Class[] classesToBeBound = {NameRequest.class};
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        return new JmsJaxbHandler(jmsTemplate, classesToBeBound);
    }


}
