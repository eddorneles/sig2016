package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EnderecoDao;
import dao.RepresentanteDao;
import model.Endereco;
import model.Representante;

/**
 * Servlet implementation class CadastroRepresentante
 */
@WebServlet("/CadastroRepresentante")
public class CadastroRepresentanteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpServletRequest req;
	HttpServletResponse resp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroRepresentanteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
    @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.req = request;
        this.resp = response;
        String nome = this.req.getParameter( "nome" );
        String rg = this.req.getParameter( "rg" );
        String strCpf = this.req.getParameter( "cpf" ) ;
        String logradouro = this.req.getParameter( "logradouro" );
        String strNumero = this.req.getParameter( "numero" );
        String cidade = this.req.getParameter( "cidade" );
        String uf = this.req.getParameter( "uf" );
        String bairro = this.req.getParameter( "bairro" );
        String strCep = this.req.getParameter( "cep" );
        String json = this.req.getParameter( "json" );

        if( nome != null && rg != null && strCpf != null && cidade != null && logradouro != null &&
                        cidade != null && uf != null && strCep != null && json != null && bairro != null ){
            
            Representante representante = new Representante();
            Endereco endereco = new Endereco();
            endereco.setEverything( logradouro, Integer.parseInt(strNumero), 
                            bairro, cidade, uf, Integer.parseInt( strCep ) );
            representante.setNome( nome );
            representante.setEndereco(endereco);
            representante.setCpf( Integer.parseInt( strCpf ) );
            representante.setRg(  rg  );
            representante.setCodPatrocinador( 0 );
            EnderecoDao enderecoDao = new EnderecoDao();
            enderecoDao.insertEndereco( endereco , representante , json );
            RepresentanteDao representanteDao = new RepresentanteDao();
            representanteDao.insertRepresentante(representante);
            System.out.println( "Saí do POST!!!" );
        }
        
    }

}
