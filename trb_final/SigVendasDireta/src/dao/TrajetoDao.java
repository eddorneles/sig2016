package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Representante;

public class TrajetoDao {
    public String insertTrajetoFromJson( Representante representante , String strJson ){
        Dao dao = new Dao();
        Connection con = dao.getConnection();
        String wkt = "NADA!";
        try{
            /* Primeiramente deve-se converter o GeoJson em WKT */
            String sql = "SELECT ST_AsText( ST_GeomFromGeoJSON('" + strJson + "')) As wkt";
            PreparedStatement prepStmt = con.prepareStatement( sql );
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            wkt = rs.getString( 1 );
            /* Com o WKT deve-se inserir no BD */
            sql = "INSERT INTO trajeto (cod_representante, geom_trajeto ) " + 
                            "VALUES ( :cod_representante , :geom_trajeto ) ";
            NamedParameterStatement namParStmt = new NamedParameterStatement( con , sql );
            namParStmt.setString( "cod_representante" , Integer.toString(representante.getCodigo() ) );
            namParStmt.setString( "geom_trajeto" , wkt );
        }catch( SQLException e ){
            System.err.println( "SQLState: " + ((SQLException)e).getSQLState() );
            System.err.println( ("Message: " + e.getMessage() ));
            e.printStackTrace();
        }
        return wkt;
    }//END inserTrajetoFromJSON
    
    public String insertTrajetoFromJson( String strJson ){
        Dao dao = new Dao();
        Connection con = dao.getConnection();
        String wkt = "NADA!";
        try{
            //String formattedJson = insertBackslashInJson(strJson);
            //System.out.println( formattedJson );
            String sql = "SELECT ST_AsText( ST_GeomFromGeoJSON('" + strJson + "')) As wkt";
            PreparedStatement prepStmt = con.prepareStatement( sql );
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            wkt = rs.getString( 1 );
            System.out.println( wkt );
        }catch( SQLException e ){
            System.err.println( "SQLState: " + ((SQLException)e).getSQLState() );
            System.err.println( ("Message: " + e.getMessage() ));
            e.printStackTrace();
        }
        return wkt;
    }//END inserTrajetoFromJSON
    
    private String insertBackslashInJson( String strJson ){
         char [] jsonFormatted = strJson.toCharArray();
         int size = jsonFormatted.length;
         char ch;
         String formattedJson = "";
         for( int i = 0 ; i < size ; i++ ){
             ch = jsonFormatted[i];
             if( ch == '"' ){
                 formattedJson += "\\";
             }
             formattedJson += ch;
         }
         return formattedJson;
    }
}
