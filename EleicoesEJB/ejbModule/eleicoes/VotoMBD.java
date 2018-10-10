package eleicoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.sql.DataSource;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "EleicoesQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "VotoMBD")
public class VotoMBD implements MessageListener {
	
	@Resource(lookup="jdbc/Eleicoes")	
	private DataSource ds = null;
	
	public void onMessage (Message message){
	
		try{
			Voto v = message.getBody(Voto.class);
			System.out.println("Voto: " + v.getIdCandidato());
			
			Connection conn = ds.getConnection();
			final String SQL = "Insert Into VOTOS(ID_CANDIDATO, DATA_HORA) Values (?,?)";
	    	PreparedStatement ps = conn.prepareStatement(SQL);
	    	ps.setInt(1, v.getIdCandidato());
	    	ps.setTimestamp(2,  new Timestamp(Calendar.getInstance().getTimeInMillis()));
	    	ps.executeUpdate();
	    	
		} catch (Exception e) {
	    	e.printStackTrace();
			
		}
	}
}
