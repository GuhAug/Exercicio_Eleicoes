package eleicoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


// http://localhost:9090/EleicoesService/rest/eleicoes 
@Path("/eleicoes")
public class EleicoesService {
	
	//@Resource(lookup="jms/EleicoesFactory")	
	//private DataSource ds = null;	
	
	Candidato c = new Candidato();

	@GET
	@Path("{numero}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Candidato getCandidato(@PathParam(value="numero")String numero) throws Exception{
		//Candidato c = new Candidato();
		//c.setNome("Teste");
		//c.setIdCandidato(1);
		//return c;
		
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("jdbc/Eleicoes");
		Connection conn = ds.getConnection();
		
		PreparedStatement ps = null;		
		try	{	
			
			ps = conn.prepareStatement("SELECT NOME, ID FROM CANDIDATOS WHERE CODIGO = ? ");					
			ps.setString(1, numero);
			ResultSet rs = ps.executeQuery();	
			if (rs.next())	{	
				
				System.out.println("Teste");
				
				String nome = rs.getString(1);
				Integer ID = rs.getInt(2);
				
				c.setNome(nome);
				c.setIdCandidato(ID);
			}
			
		} catch (Exception e) {
	    	e.printStackTrace(); 
		
		} finally {
			if (ps != null)	{	
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}	
		} return c; 	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String Votar(Voto v) throws Exception{
	
	Context ctx = new InitialContext();
	ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/EleicoesFactory");
	JMSContext context = connectionFactory.createContext();
	Queue queue = (Queue) ctx.lookup("jms/EleicoesQueue");
		
	context.createProducer().send(queue, v);
	
	return "Adicionando ao queue: " +v.getIdCandidato();
	
	}
	
}
