package br.com.soaexpert.camelexpert1;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


/**
 *
 * @author luisffc
 */
public class FileCopy {

    public static void main(String[] args) throws Exception{
        
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder(){
            public void configure(){

                from("file:/home/soaexpert/data/inbox")
                        .to("file:/home/soaexpert/data/outbox");

            }

        });//route

        context.start();
        Thread.sleep(10000);
        context.stop();
    }
}
