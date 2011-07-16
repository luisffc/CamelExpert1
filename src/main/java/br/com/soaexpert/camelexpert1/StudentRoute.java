package br.com.soaexpert.camelexpert1;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author soaexpert
 */
public class StudentRoute{

    public static void main(String[] args) throws Exception{
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder(){
            public void configure(){
                from("file:/home/soaexpert/data/inbox").to("jms:alunosQueue");

                from("jms:alunosQueue")
                        .choice()
                        .when(header("CamelFileName").endsWith(".xml"))
                        .to("jms:alunosXML")

                        .choice()
                        .when(header("CamelFileName").endsWith(".csv"))
                        .to("jms:alunosCSV");

                from("jms:alunosXML").process(new Processor(){
                   public void process(Exchange exchange) throws Exception{
                       System.out.println("Arquivo chegando: "+
                               exchange.getIn().getHeader("CamelFileName"));
                   }
                });
            }
        });
        
    }
}
