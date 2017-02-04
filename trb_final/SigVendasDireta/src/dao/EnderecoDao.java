package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Endereco;
import model.Representante;

import org.postgis.*;
import org.postgresql.PGConnection;


public class EnderecoDao {
    public void insertEndereco( Endereco endereco , Representante representante , String json ){
        if( endereco != null && representante != null ){
            Dao dao = new Dao();
            String wkt = dao.retrieveWktFromGeoJson( json );
            System.out.println(wkt);
            Connection con = dao.getConnection();
            
            try{
                System.out.println( wkt );
                String geom = "ST_GeomFromText('" + wkt + "', 4326 )";
                
                String sql = " INSERT INTO endereco ( logradouro , numero , bairro , uf ," +
                                " cep , cpf_representante ,geom_endereco )" +
                                "VALUES ( ? , ? , ? , ?, ? , ? ," + geom + ")";
                PreparedStatement prepStmt = con.prepareStatement( sql );
                int i = 1;
                prepStmt.setString( 1, endereco.getLogradouro() );
                prepStmt.setInt( 2 , endereco.getNumero() );
                prepStmt.setString( 3, endereco.getBairro() );
                prepStmt.setString( 4, endereco.getUf() );
                prepStmt.setInt( 5 , endereco.getCep() );
                prepStmt.setInt( 6, representante.getCpf() );
                //prepStmt.setString( i++ , wkt );
                prepStmt.executeUpdate();
                dao.closeConnection();
            }catch( SQLException e ){
                System.err.println( "SQLState: " + (( SQLException)e).getSQLState() );
                System.err.println( ("Message: " + e.getMessage() ));
                e.printStackTrace();
            }//END try
        }
    }//END insertEndereco

}
