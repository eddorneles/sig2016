package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.lookup.ReductionResult;

public class QueryStringDao {
    private String syntaxErrorMessage;
    private boolean syntaxError;
    
    public Map<String, List<String> > queryFromString( String sql ){
        Dao dao = new Dao();
        Connection con = dao.getConnection();
        
        Map<String, List<String> > mapTabelaDados;
        try{
            Statement prepStmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE ,
                            ResultSet.CONCUR_READ_ONLY );
            ResultSet rs = prepStmt.executeQuery( sql );
            ResultSetMetaData rsmd = rs.getMetaData();
            /* Instanciação de um map<chave: nome da coluna)(valor:lista de dados>*/
            mapTabelaDados = new HashMap<String , List<String> >();
            int numColumns = rsmd.getColumnCount();
            /* Deve-se começar a iterar de 1 pois é o primeiro índice do ResultSet */
            for( int  j = 1 ; j <= numColumns ; j++ ){
                String curColumnName = rsmd.getColumnName( j );
                List<String> listaDados =  new ArrayList<String>();
                while( rs.next() ){
                    String strData = rs.getString( curColumnName );
                    listaDados.add( strData );
                }
                //Reposiciona o cursor do ResultSet para antes do início
                rs.beforeFirst();
                /* Após adicionar todos os elementos retornados pela consulta, à lista
                     deve-se adicionar a lista ao Map com a chave correspondente */
                mapTabelaDados.put( curColumnName, listaDados );
            }//for ( i = 0 ...
        }catch( SQLException e ){
            this.syntaxErrorMessage = e.getMessage();
            System.err.println( "SQLState: " + ((SQLException)e).getSQLState() );
            System.err.println( ("Message: " + e.getMessage() ));
            this.syntaxError = true;
            return null;
        }
        return mapTabelaDados;
    }
    
    public String getSyntaxErrorMessage(){
        return this.syntaxErrorMessage;
    }
    
    public boolean hadSyntaxError() {
        return this.syntaxError;
    }
}
