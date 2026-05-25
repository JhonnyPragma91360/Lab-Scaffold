package co.com.bancolombia.mq.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlConfig {
    @Bean
    public XmlMapperWrapper xmlMapperWrapper() {
        var xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION,true);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return new XmlMapperWrapper(xmlMapper);
    }
}