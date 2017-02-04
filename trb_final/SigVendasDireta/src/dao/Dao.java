package dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.postgresql.*;
import org.postgis.DriverWrapper;


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

    /** 
     * Descrição: Converte uma string no formato GeoJSON e converte para 
     * uma string no formato WKT
     * @param json String no formato GeoJSON
     * @return wkt
     */
    protected String retrieveWktFromGeoJson( String json ){
        String wkt = "";
        try{
            String sql = "SELECT ST_AsText( ST_GeomFromGeoJSON('" + json + "')) As wkt";
            this.con = this.getConnection();
            PreparedStatement prepStmt = this.con.prepareStatement( sql );
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            wkt = rs.getString( 1 );
        }catch( SQLException e ){
            System.err.println( "SQLState: " + ((SQLException)e).getSQLState() );
            System.err.println( ("Message: " + e.getMessage() ));
            e.printStackTrace();
        }
        return wkt;
    }

}//END Class Dao
