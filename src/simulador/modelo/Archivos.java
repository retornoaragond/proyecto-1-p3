/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Archivos {

    public static void guardar_xml(String nombreArchivo, Maquina maquina)  {
        try {
            String nombre = nombreArchivo + ".xml";
            JAXBContext jaxbContext = JAXBContext.newInstance(Maquina.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(maquina,System.out);
            //para guardar la maquina en un archivo xml
        } catch (JAXBException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Nodo> recuperar_xml(String nombreArchivo){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Maquina.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Maquina maquina = (Maquina) unmarshaller.unmarshal(new File(nombreArchivo));
            //para recuperar los nodos de la maquina desde un archivo xml
            return null;
        } catch (JAXBException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
