package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BairroDao;
import dao.QueryStringDao;
import model.Bairro;
import util.Padronizador;

/**
 * Servlet implementation class IndexControl
 */

@WebServlet("/IndexControl")
public class IndexControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_CHARACTERS = 45;
    
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private String html="";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
        // TODO Auto-generated method stub
	    this.req = request;
	    this.resp = response;
        
        if( request.getParameterMap().size() > 0 ){
            /* Durante a execução de handleParameters o atributo html é setado para
             * para ser utilizado no this.resp.getWriter()... */
            this.handleParameters();
            Padronizador.padronizaTextoResposta( this.resp );
            this.resp.getWriter().write( this.html );
        }else{
            String txt = "Funcionoooooooooooou!!! Agora é só correr pro abraço!</br></br>";
            String htmlText = txt + createHtmlAllBairros();
            Padronizador.padronizaTextoResposta( response );
            response.getWriter().write( htmlText );
        }
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    
    /*********************************************************************
     * Descrição: Método responsável por tratar os parâmetros passados 
     * pela requisição AJAX e encaminhar para o método aproprioado
     */
    private void handleParameters(){
        /* Se a requisição ajax passou um parâmetro chamdo "metodo" 
         * que é igual à consultaSql */
        String parMetodo = this.req.getParameter( "metodo" );
        /* Essa forma é preferível pois evita NullPointerException */
        if( "createHtmlFromQuery".equals( parMetodo ) ){
            this.html = createHtmlFromQuery();
            return;
        }
        return;
    }
    
    private String createHtmlFromQuery(){
        String sql = this.req.getParameter( "sql" );
        QueryStringDao queryStrDao = new QueryStringDao();
        Map<String,List<String>> mapTabelaDados = queryStrDao.queryFromString( sql );
        
        /* É necessário verificar se a consulta foi bem sucedida*/
        if( queryStrDao.hadSyntaxError() != true ){
            //Obtém-se o número de linhas que foram registradas com as duas próximas linhas
            Map.Entry<String, List<String>> entry = mapTabelaDados.entrySet().iterator().next();
            int numRows = entry.getValue().size();
            
            String tagTr = "<tr>";
            String tagTh = "<th>";
            String tagTd = "<td>";
            
            String html = "<table>";            
            html += tagTr;
            for( Map.Entry<String, List<String> > tmpEntry : mapTabelaDados.entrySet() ){
                html += tagTh + tmpEntry.getKey() + "</th>";
            }
            html += "</tr>";
            /* Itera-se sobre o número de linhas que existe na tabela,
             * pois tabelas html são construídas de linha em linha */
            for( int i = 0 ; i < numRows ; i++ ){
                html += tagTr;
                /* Itera-se sobre cada elemento do Map para percorrer as colunas */
                for( Map.Entry<String, List<String> > tmpEntry : mapTabelaDados.entrySet() ){
                    String strData = tmpEntry.getValue().get(i);
                    if( strData.length() > MAX_CHARACTERS ) {
                        strData = strData.substring( 0 , MAX_CHARACTERS ) + "+++";
                    }
                    html += tagTd + strData + "</td>";
                }
                html += "</tr>";
            }//END for( int i = 0 ...
            html += "</table>";
            return html;
        }//END queryStrDao.hadSyntaxError() != true
        //Se o mapRetornado for null, então não foi possível completar a consulta
        return "OCORREU UM ERRO NA CONSULTA<br/><br/>" + queryStrDao.getSyntaxErrorMessage();
    }//END createHtmlFromQuery()
    
    private String createHtmlAllBairros(){
        BairroDao bairroDao = new BairroDao(); 
        List<Bairro> listaBairros = bairroDao.retrieveAllBairros();
        
        String htmlText = "<h2>Listagem de Bairros</h2>";
        htmlText += "<table border=\"1\" cellspacing=10 cellpadding=5>" +
                        "<th>Código Bairro</th><th>Nome Bairro</th>";
        
        
        for( Bairro bairroAtual : listaBairros ){
            htmlText += "<tr><td>" + bairroAtual.getCodigo() + "</td>" +
                            "<td>" + bairroAtual.getNome() + "</tr></td>";
        }
        htmlText += "</table>";
        return htmlText;
    }


}//END class 