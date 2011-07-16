package br.com.soaexpert.camelexpert1;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author luis
 */
public class FtpToJms {

    public static void main(String[] args) throws Exception{

        CamelContext context = new DefaultCamelContext();

        ConnectionFactory cf = new ActiveMQConnectionFactory("vm://localhost");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(cf));

        context.addRoutes(new RouteBuilder() {
            public void configure(){
                from("ftp://starttrek.seasonsp.intranet/username=felipe&password=secret")

                        .process(new Processor(){
                            public void process(Exchange exchange) throws Exception{
                                System.out.println("Nome do arquivo: " +
                                        exchange.getIn().getHeader("CamelFileName"));
                            }
                        })

                        .to("jms:matriculaAlunos");
            }
        });
        
    }
}
