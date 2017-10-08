/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.modelo;

import java.io.File;
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

    public static void guardar_xml(String nombreArchivo, Maquina maquina) {
        try {
            String nombre = nombreArchivo + ".xml";
            JAXBContext jaxbContext = JAXBContext.newInstance(Maquina.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(maquina, new FileWriter(nombre));
            //para guardar la maquina en un archivo xml
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Maquina recuperar_xml(String nombreArchivo) {
        Maquina maquina = new Maquina();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Maquina.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Maquina maquina1 = (Maquina) unmarshaller.unmarshal(new File(nombreArchivo));
            maquina.setEstinicio(maquina1.getEstinicio());
            maquina.setmaquina(maquina1.getmaquina());
        } catch (JAXBException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return maquina;
    }
}
