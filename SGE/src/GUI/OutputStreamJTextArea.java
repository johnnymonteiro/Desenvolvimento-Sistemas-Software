package GUI;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 *
 * @author willi
 */
public class OutputStreamJTextArea extends OutputStream{
    private final JTextArea textArea;

    public OutputStreamJTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirecionar data para TextArea
        textArea.append(String.valueOf((char)b));
        // scroll do textArea para o fim do data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

   
    
    
}
