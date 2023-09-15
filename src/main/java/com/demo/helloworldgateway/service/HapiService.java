package com.demo.helloworldgateway.service;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;

import java.io.*;

/**
 * La classe `HapiService` è responsabile della creazione, formattazione e serializzazione di messaggi
 * HL7 utilizzando la libreria HAPI (Healthcare API for Java).
 * createMessage(), esegue le seguenti operazioni:
 * 1. Configura l'ambiente HAPI attraverso l'istanza di `HapiContext`.
 * 2. Crea un messaggio HL7 di tipo ADT A01 utilizzando la classe `AdtMessageFactory`.
 * 3. Prepara parser HL7 per operazioni di formattazione e serializzazione.
 * 4. Formatta il messaggio in una stringa di messaggio HL7.
 * 5. Serializza il messaggio in file di output sia in formato delimitato da pipe che XML.
 * 6. Stampa la struttura del messaggio a scopo informativo.
 *
 * Questa classe utilizza la libreria HAPI per operare su messaggi HL7 e consente la creazione di messaggi
 * di diversi tipi estendendo il supporto in `AdtMessageFactory`.
 */
public class HapiService{

    /**
     * Questo metodo statico crea un messaggio HL7 di tipo ADT A01, formatta e serializza il messaggio in
     * file di output e stampa la struttura del messaggio.
     *
     * @throws HL7Exception Se si verificano errori durante la creazione del messaggio HL7.
     * @throws IOException  Se si verificano errori di input/output durante la creazione del messaggio.
     * @throws RuntimeException Se il tipo di messaggio specificato non è supportato.
     */
    public static void createMessage()throws HL7Exception,IOException,RuntimeException{

             HapiContext context = new DefaultHapiContext();
             System.out.println("Creating ADT A01 message...");
             ADT_A01 adtMessage = (ADT_A01) AdtMessageFactory.createMessage("A01");

            // creo dei parser per le operazioni

            Parser pipeParser = context.getPipeParser();
            Parser xmlParser = context.getXMLParser();
            System.out.println("Message was constructed successfully..." + "\n");

            // encode() --> formatta un oggetto Message in una stringa di messaggio HL7
            System.out.println(pipeParser.encode(adtMessage));

            // serializza il messaggio in un file (txt,XML)
            // writeMessageToFile(Parser parser, ADT_A01 adtMessage, String outputFilename)
            writeMessageToFile(pipeParser, adtMessage, "testPipeDelimitedOutputFile.txt");
            writeMessageToFile(xmlParser, adtMessage, "testXmlOutputFile.xml");
            System.out.println("Printing message....");
            System.out.println(adtMessage.printStructure());



    }

    /**
     * Questo metodo privato gestisce la scrittura del messaggio serializzato in un file.
     *
     * @param parser        Parser per la formattazione del messaggio.
     * @param adtMessage    Messaggio HL7 da scrivere nel file.
     * @param outputFilename Nome del file di output.
     * @throws IOException   Se si verificano errori di input/output durante la scrittura del file.
     * @throws HL7Exception Se si verificano errori durante la formattazione del messaggio HL7.
     */
    private static void writeMessageToFile(Parser parser, ADT_A01 adtMessage, String outputFilename)
            throws IOException, HL7Exception {
        OutputStream outputStream = null;
        try {

            File file = new File(outputFilename);

            // controlla se il file non esiste già
            if (!file.exists()) {
                file.createNewFile();
            }

            System.out.println("Serializing message to file...");
            outputStream = new FileOutputStream(file);
            outputStream.write(parser.encode(adtMessage).getBytes());
            outputStream.flush();

            System.out.printf("Message serialized to file '%s' successfully", file);
            System.out.println("\n");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


}
