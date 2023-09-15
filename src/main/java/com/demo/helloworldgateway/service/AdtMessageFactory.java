package com.demo.helloworldgateway.service;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;

import java.io.IOException;

/**
 * La classe `AdtMessageFactory` è utilizzata per creare messaggi HL7 di diversi tipi in base al
 * tipo specificato come argomento. Questo supporta la creazione di messaggi ADT A01.
 */
public class AdtMessageFactory {

    /**
     * Crea e restituisce un messaggio HL7 del tipo specificato.
     *
     * @param messageType Tipo di messaggio HL7 da creare (es. "A01").
     * @return Un oggetto `Message` rappresentante il messaggio HL7 costruito.
     * @throws HL7Exception Se si verificano errori durante la creazione del messaggio HL7.
     * @throws IOException  Se si verificano errori di input/output durante la creazione del messaggio.
     * @throws RuntimeException Se il tipo di messaggio specificato non è supportato.
     */
    public static Message createMessage(String messageType) throws HL7Exception, IOException,RuntimeException {

        //Questo modello consente di creare altri tipi di messaggi
        if ( messageType.equals("A01") )
        {
            return new AdtA01MessageBuilder().build();
        }

        // qui altri tipi di messaggi, esempio AdtA02MessageBuilder().Build();

        throw new RuntimeException(String.format("%s message type is not supported yet. Extend this if you need to", messageType));

    }
}
