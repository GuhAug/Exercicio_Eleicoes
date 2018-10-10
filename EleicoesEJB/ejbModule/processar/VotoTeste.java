package processar;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import eleicoes.Voto;

public class VotoTeste {

	public static void main(String[] args) throws NamingException {

		
		Context ctx = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/EleicoesFactory");
		JMSContext context = connectionFactory.createContext();
		Queue queue = (Queue) ctx.lookup("jms/EleicoesQueue");
		
		Voto v = new Voto();
		v.setIdCandidato(2);
		
		context.createProducer().send(queue, v); 

	}

}
