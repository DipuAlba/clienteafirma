package es.gob.afirma.miniapplet.actions;

import java.io.IOException;
import java.io.InputStream;

import javax.jnlp.FileOpenService;

import es.gob.afirma.core.AOCancelledOperationException;
import es.gob.afirma.core.misc.AOUtil;
import es.gob.afirma.core.misc.Base64;
import es.gob.afirma.miniapplet.ui.FileSelectionDialog;

/**
 * Acci&oacute;n para la recuperaci&oacute;n del contenido de un fichero.
 * @author Carlos Gamuci Mill&aacute;n
 */
public class GetFileContentAction {

    private FileOpenService jnlpFos;
    
    /**
     * Crea la acci&oacute;n en base a un servicio JNLP para la carga de ficheros.
     * @param fos Servicio de carga de ficheros.
     */
    public GetFileContentAction(final FileOpenService fos) {
        this.jnlpFos = fos;
    }
    
    /**
     * Recupera el contenido del fichero codificado en base 64.
     * @return El contenido del fichero.
     * @throws AOCancelledOperationException Cuando se cancela la operacion de selecci&oacute;n.
     * @throws IOException Cuando se produce un error al leer el fichero.
     */
    public String getResult() throws AOCancelledOperationException, IOException {
        FileSelectionDialog dialog = new FileSelectionDialog(this.jnlpFos);
        InputStream is = dialog.getFileContent();
        byte[] content = AOUtil.getDataFromInputStream(is);
        try {
            is.close();
        } catch (Exception e) {
            /* Ignoramos este error */
        }
        
        return Base64.encodeBytes(content);
    }
}
