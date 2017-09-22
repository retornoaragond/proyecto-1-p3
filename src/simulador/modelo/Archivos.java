/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.modelo;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Archivos {

    public static void guardar_xml(String nombreArchivo,Maquina maquina)
            throws ParserConfigurationException,
            TransformerConfigurationException,
            TransformerException {
        //para guardar la maquina en un archivo xml
    }

    public static ArrayList<Nodo> recuperar_xml(String nombreArchivo)
            throws ParserConfigurationException,
            TransformerConfigurationException,
            TransformerException {
        //para recuperar los nodos de la maquina desde un archivo xml
        return null;
    }
}
