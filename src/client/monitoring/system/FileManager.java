/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.monitoring.system;

/*import com.socket.Message;*/
import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class FileManager {

    public String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveFile( String time) {

        try {
           

        } catch (Exception ex) {
            System.out.println("Exceptionmodify xml");
        }
    }

}
