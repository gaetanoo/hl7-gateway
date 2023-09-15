## GENERAL INFORMATION 

Questa applicazione è un esempio di utilizzo di Spring Boot e della libreria HAPI (Healthcare API for Java) 
per la gestione e la creazione di messaggi HL7. L'applicazione comprende diverse classi tra cui :

Un controller Spring Boot che espone un endpoint per la creazione di messaggi HL7. (HapiController),
una classe Service che crea e gestisce la creazione di messaggi HL7 utilizzando la libreria HAPI. (HapiService),
una classe factory per la creazione di diversi tipi di messaggi HL7. (AdtMessageFactory),
una classe per la costruzione di messaggi HL7 di tipo ADT A01. (AdtA01MessageBuilder).

## CONFIGURATION 

La configurazione principale dell'applicazione è gestita attraverso il file application.yaml
# Porta su cui il server Spring Cloud Gateway ascolta le richieste HTTP.
port: 8080
# Definizione delle route per il reindirizzamento delle richieste
spring.cloud.gateway.routes:

