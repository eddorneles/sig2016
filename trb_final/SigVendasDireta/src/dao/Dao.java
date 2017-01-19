package dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {
	
	static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/sig_vendas/";
	
	private Context ctx = null;
	private Context envCtx = null;
    private Connection con = null;
    private DataSource ds = null;
    
	protected Connection getConnection(){
	    
	    try{
	        this.ctx = new InitialContext();
	        this.envCtx = (Context) ctx.lookup( "java:/comp/env" );
	        this.ds = (DataSource) envCtx.lookup( "jdbc/sig_vendas" );
	        this.con = ds.getConnection(); //Estabelece comunicação com o BD de fato
	    }catch( NamingException e ){
	        //Se não foi possível localizar o serviço de nomeação
	        e.printStackTrace();
	    }catch( SQLException e ){
	        //Se não foi estabelecida comunicação com o bd 
	        e.printStackTrace();
	    }
	    return con;
	}//END getConnection
	
	protected void closeConnection(){
	    try{
	        
	        this.con.close();
	        this.ctx.close();
	    }catch( SQLException e){
	        e.printStackTrace();
	    }catch( NamingException e ){
	        e.printStackTrace();
	    }
	}//END closeConnection
	
	
}//END Class Dao
