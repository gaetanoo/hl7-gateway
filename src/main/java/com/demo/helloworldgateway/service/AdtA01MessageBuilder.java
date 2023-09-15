package com.demo.helloworldgateway.service;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v23.datatype.PL;
import ca.uhn.hl7v2.model.v23.datatype.XAD;
import ca.uhn.hl7v2.model.v23.datatype.XCN;
import ca.uhn.hl7v2.model.v23.datatype.XPN;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.model.v23.segment.EVN;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.model.v23.segment.PV1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * La classe `AdtA01MessageBuilder` è utilizzata per costruire un messaggio HL7 del tipo ADT A01
 * (Admission/Registration). Questa classe offre un'interfaccia per creare facilmente un messaggio
 * HL7 con segmenti MSH, EVN, PID e PV1 con dati di esempio.
 */
public class AdtA01MessageBuilder {


    private ADT_A01 adtMessage;

    /**
     * Costruisce e restituisce un messaggio ADT A01 completo con segmenti MSH, EVN, PID e PV1.
     *
     * @return Un oggetto `ADT_A01` rappresentante il messaggio HL7 costruito.
     * @throws HL7Exception Se si verificano errori durante la costruzione del messaggio HL7.
     * @throws IOException  Se si verificano errori di input/output durante la costruzione del messaggio.
     */

    public ADT_A01 build() throws HL7Exception, IOException {
        String currentDateTimeString = getCurrentTimeStamp();

        adtMessage = new ADT_A01();
        adtMessage.initQuickstart("ADT", "A01", "P");
        createMshSegment(currentDateTimeString);
        createEvnSegment(currentDateTimeString);
        createPidSegment();
        createPv1Segment();
        return adtMessage;
    }

    /**
     * Crea il segmento MSH (Message Header) del messaggio HL7 con i dati di esempio preimpostati.
     *
     * @param currentDateTimeString Stringa rappresentante la data e l'ora correnti formattate.
     * @throws DataTypeException Se si verificano errori durante la creazione del segmento MSH.
     */
    private void createMshSegment(String currentDateTimeString) throws DataTypeException {
        MSH mshSegment = adtMessage.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getSendingApplication().getNamespaceID().setValue("Sistema di partenza");
        mshSegment.getSendingFacility().getNamespaceID().setValue("Infrastruttura di partenza");
        mshSegment.getReceivingApplication().getNamespaceID().setValue("Sistema di arrivo");
        mshSegment.getReceivingFacility().getNamespaceID().setValue("Infrastruttura di arrivo");
        mshSegment.getDateTimeOfMessage().getTimeOfAnEvent().setValue(currentDateTimeString);
        mshSegment.getMessageControlID().setValue(getSequenceNumber());
        mshSegment.getVersionID().setValue("2.4");
    }

    /**
     * Crea il segmento EVN (Event Type) del messaggio HL7 con i dati di esempio preimpostati.
     */
    private void createEvnSegment(String currentDateTimeString) throws DataTypeException {
        EVN evn = adtMessage.getEVN();
        evn.getEventTypeCode().setValue("A01");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(currentDateTimeString);
    }

    /**
     * Crea il segmento PID (Patient Identification) del messaggio HL7 con i dati di esempio preimpostati.
     */
    private void createPidSegment() throws DataTypeException {
        PID pid = adtMessage.getPID();
        XPN patientName = pid.getPatientName(0);
        patientName.getFamilyName().setValue("Gaetano");
        patientName.getGivenName().setValue("Cassalia");
        pid.getPatientIDExternalID().getID().setValue("378785433211");
        XAD patientAddress = pid.getPatientAddress(0);
        patientAddress.getStreetAddress().setValue("Via Mazzini 93");
        patientAddress.getCity().setValue("Reggio Calabria");
        patientAddress.getStateOrProvince().setValue("Calabria");
        patientAddress.getCountry().setValue("IT");
    }

    /**
     * Crea il segmento PV1 (Patient Visit) del messaggio HL7 con i dati di esempio preimpostati.
     */
    private void createPv1Segment() throws DataTypeException {
        PV1 pv1 = adtMessage.getPV1();
        pv1.getPatientClass().setValue("O");
        PL assignedPatientLocation = pv1.getAssignedPatientLocation();
        assignedPatientLocation.getFacility().getNamespaceID().setValue("Nome Centro Sanitario");
        assignedPatientLocation.getPointOfCare().setValue("Punto di Cura (Point of Care)");
        pv1.getAdmissionType().setValue("ALERT");
        XCN referringDoctor = pv1.getReferringDoctor(0);
        referringDoctor.getIDNumber().setValue("99999999");
        referringDoctor.getFamilyName().setValue("Mario");
        referringDoctor.getGivenName().setValue("Rossi");
        referringDoctor.getIdentifierTypeCode().setValue("456789");
        pv1.getAdmitDateTime().getTimeOfAnEvent().setValue(getCurrentTimeStamp());
    }

    /**
     * Restituisce una stringa rappresentante la data e l'ora correnti formattate come "yyyyMMddHHmmss".
     *
     * @return Una stringa rappresentante la data e l'ora correnti formattate.
     */
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * Genera un numero di sequenza basato sulla data e sull'ora correnti e un prefisso arbitrario.
     *
     * @return Una stringa rappresentante il numero di sequenza generato.
     */
    private String getSequenceNumber() {
        String facilityNumberPrefix = "1234"; // some arbitrary prefix for the facility
        return facilityNumberPrefix.concat(getCurrentTimeStamp());
    }
}
