package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BairroDao;
import model.Bairro;
import util.Padronizador;

/**
 * Servlet implementation class IndexControl
 */

@WebServlet("/IndexControl")
public class IndexControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
        String txt = "Funcionoooooooooooou!!! Agora é só correr pro abraço!</br></br>";
        String htmlText = txt + createHtmlAllBairros();
        Padronizador.padronizaTextoResposta( response );
        response.getWriter().write( htmlText );
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    
    private String createHtmlAllBairros(){
        BairroDao bairroDao = new BairroDao(); 
        ArrayList<Bairro> listaBairros = bairroDao.retrieveAllBairros();
        
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