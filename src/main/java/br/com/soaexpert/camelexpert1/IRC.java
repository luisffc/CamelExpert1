package br.com.soaexpert.camelexpert1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.irc.IrcComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author soaexpert
 */
public class IRC {

    public static void main(String[] args) throws Exception{
        CamelContext context = new DefaultCamelContext();
        context.addComponent("irc", new IrcComponent());

        context.addRoutes(new RouteBuilder() {

            public void configure() throws Exception {
                .from("irc:soaexpert23@irc.freenode.net?channels=#soa,#neo4j")
                .process(null)
            }
        });

    }
}
