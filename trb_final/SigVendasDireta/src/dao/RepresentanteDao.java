package dao;

import model.Endereco;
import model.Representante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepresentanteDao {
    
    public void insertRepresentante( Representante representante ){
        if( representante != null ){
            Dao dao = new Dao();
            Connection con = dao.getConnection();

            try{
                String sql = "INSERT INTO representante (nome, cpf, rg, cod_patrocinador) " + 
                                "VALUES ( :nome , :cpf , :rg , :cod_patrocinador) ";
                NamedParameterStatement namParStmt = new NamedParameterStatement( con, sql );
                namParStmt.setString( "nome" , representante.getNome() );
                namParStmt.setInt( "cpf" , representante.getCpf() );
                namParStmt.setString( "rg" , representante.getRg() );
                namParStmt.setInt( "cod_patrocinador" , representante.getCodPatrocinador() );
                namParStmt.executeUpdate();
                dao.closeConnection();
            }catch( SQLException e ){
                System.err.println( "SQLState: " + (( SQLException)e).getSQLState() );
                System.err.println( ("Message: " + e.getMessage() ));
                e.printStackTrace();
            }
        }
    }//END insertRepresentanteEndereco

}
