package util;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ed-dorneles
 *
 */
public class Padronizador {
    
    /**************************************************************************
     * Descrição: Seta o ContentType de resposta para "text/html" e o Character
     *              Enconding para UTF-8
     * @param resposta : HttpServletResponse
     */
    public static void padronizaTextoResposta( HttpServletResponse resposta ){
        resposta.setContentType( "text/html" );
        resposta.setCharacterEncoding( "UTF-8" );
    }
}
