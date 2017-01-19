package dao;

import model.Bairro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

public class BairroDao {
    
    public List<Bairro> retrieveAllBairros(){
        
        Dao dao = new Dao();
        Connection connection = dao.getConnection();
        
        //Somente instanciar como ArrayList a partir de então usar como List
        List<Bairro> listaBairros = new ArrayList<Bairro>();
        try{
            String sql = " SELECT * FROM bairro ";
            PreparedStatement prepStmt = connection.prepareStatement( sql );
            ResultSet rs = prepStmt.executeQuery();
            while( rs.next() ){
                    Bairro bairro = new Bairro();
                    bairro.setCodigo( rs.getString( "cod_bairro" ) );
                    bairro.setNome( rs.getString( "nom_bairro" ) );
                    listaBairros.add( bairro );
                }//END while
        }catch( SQLException e ){
            System.err.println( "SQLState: " + ((SQLException)e).getSQLState() );
            System.err.println( ("Message: " + e.getMessage() ));
            e.printStackTrace();
        }
        finally{
            // Deve-se fechar a conexão
            dao.closeConnection();
        }
        return listaBairros;
    }//END retrieveAllBairros
    
    public List<Bairro> intersectionBairro( Bairro bairro  ){
        Connection connection = new Dao().getConnection();
        List<Bairro> listaBairros =  new ArrayList<Bairro>();
        String sql = 
                "SELECT * FROM bairro b1, bairro b2 " +
                        "WHERE ST_Intersects( b1.geom_bairro, b2.geom_bairro) " +
                        "AND b1.cod_bairro = :cod_bairro;";
        NamedParameterStatement namParStmt;
        try {
            
            namParStmt = new NamedParameterStatement( connection, sql );
            namParStmt.setString("cod_bairro", bairro.getCodigo() );
            ResultSet rs = namParStmt.executeQuery();
            
            while( rs.next() ){
                Bairro novoBairro = new Bairro();
                novoBairro.setCodigo( rs.getString( "cod_bairro" ) );
                novoBairro.setNome( rs.getString( "nom_bairro" ) );
                listaBairros.add( novoBairro );
            }//END while
            namParStmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                connection.close();
            } catch( SQLException e ){
                e.printStackTrace();
            }
        }//END try finally
        return listaBairros;
    }//END intersectionBairro()
    
}//END class
