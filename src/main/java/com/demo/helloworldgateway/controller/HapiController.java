package com.demo.helloworldgateway.controller;

import ca.uhn.hl7v2.HL7Exception;
import com.demo.helloworldgateway.service.HapiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Il controller `HapiController` gestisce le richieste relative alla creazione di messaggi HL7
 */
@RestController
@RequestMapping("/hl7")
public class HapiController {

    /**
     * Questo metodo gestisce le richieste GET per creare un messaggio HL7.
     *
     * @return Un messaggio di conferma indicante che il messaggio HL7 è stato creato con successo.
     * @throws HL7Exception Se si verificano errori durante la creazione del messaggio HL7.
     * @throws IOException  Se si verificano errori di input/output durante la creazione del messaggio.
     */
    @GetMapping("/createMessage")
    public String createHL7Message() throws HL7Exception, IOException {
        HapiService.createMessage();
        return "HL7 message created successfully!";
    }
}
