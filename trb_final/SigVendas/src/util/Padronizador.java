package util;

import javax.servlet.http.HttpServletResponse;

public class Padronizador {
    public static void padronizaTextoResposta( HttpServletResponse resposta ){
        resposta.setContentType( "text/plain" );
        resposta.setCharacterEncoding( "UTF-8" );
    }
}
