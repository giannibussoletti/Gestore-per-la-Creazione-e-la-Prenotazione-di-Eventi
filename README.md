# Gestore per la Creazione e la Prenotazione di Eventi

Un backend sviluppato in Spring Boot per la gestione completa di eventi. Il sistema permette a due diverse tipologie di utenti (Utente standard e Organizzatore) di interagire con la piattaforma in base ai propri permessi, gestendo l'intero ciclo di vita di un evento e delle relative prenotazioni.

---

## 🎯 Funzionalità Principali

Il sistema è basato su ruoli (Role-Based Access Control) protetto da autenticazione JWT:

**Organizzatore:**
* Creazione di nuovi eventi.
* Modifica o cancellazione degli eventi creati.
* Visualizzazione di un pannello personale con tutti i propri eventi.

**Utente:**
* Esplorazione degli eventi disponibili.
* Prenotazione del proprio posto a un evento.
* Cancellazione (annullamento) della propria prenotazione.
* Visualizzazione dello storico delle proprie prenotazioni.

---

## 🛠 Tecnologie Utilizzate

* **Java:** Versione 25
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Spring Security)
* **Database:** PostgreSQL
* **Sicurezza:** JWT (JSON Web Token) per l'autenticazione e l'autorizzazione

---

## 🚀 Guida all'Avvio (Per Principianti)

Segui questi passaggi per scaricare e avviare il progetto sul tuo computer.

### 1. Prerequisiti
Prima di iniziare, assicurati di aver installato sul tuo computer:
* **Java 25** (JDK)
* **PostgreSQL** (e un client visivo come pgAdmin o DBeaver per comodità)
* **IDE Java** (IntelliJ IDEA, Eclipse o VS Code)
* **Postman** (per testare le richieste API)

### 2. Configurazione del Database
1. Apri PostgreSQL e crea un nuovo database vuoto (es. chiamalo `gestione_eventi`).
2. Non preoccuparti di creare le tabelle: ci penserà Hibernate in automatico al primo avvio grazie alla proprietà `ddl-auto=update`.

### 3. Configurazione dell'Ambiente
Il progetto utilizza un file esterno per proteggere le credenziali sensibili. 
1. Apri la cartella principale del progetto (la stessa dove si trova il file `pom.xml`).
2. Crea un nuovo file e chiamalo esattamente **`env.properties`**.
3. Incolla all'interno di questo file la seguente configurazione, sostituendo i valori con i tuoi:

PORT=PORTA_A_SCELTA(Es. 5000 )
DB_URL=jdbc:postgresql://localhost:PORTA_A_SCELTA/nome_del_tuo_database
DB_USERNAME=postgres
DB_PASSWORD=la_tua_password_di_postgres
JWT_SECRET=inserisci_qui_una_chiave_segreta_molto_lunga_e_complessa_per_i_token

### 4. Avvio dell'Applicazione

* Apri il progetto con il tuo IDE preferito (es. IntelliJ IDEA, Eclipse, VS Code).
* Attendi il completamento del download delle dipendenze da parte di Maven.
* Avvia l'applicazione eseguendo la classe principale annotata con `@SpringBootApplication`.
* In alternativa, utilizza il terminale integrato ed esegui il comando `./mvnw spring-boot:run`.
* Verifica nella console che l'applicazione sia partita correttamente sulla porta specificata nel file delle proprietà (es. `8080`).

---

## 🧪 Testare il progetto con Postman

Poiché si tratta di un'applicazione backend senza interfaccia grafica, ti servirà **Postman** (o un client REST simile) per interfacciarti con le funzionalità.

* **Registrazione (Signup):** Invia una richiesta `POST` all'indirizzo `http://localhost:8080/auth/signup`. Nel *Body* (selezionando il formato JSON), inserisci i dati per creare un nuovo utente o organizzatore.
* **Accesso (Login):** Invia una richiesta `POST` all'indirizzo `http://localhost:8080/auth/login` contenente le credenziali appena create. La risposta conterrà il tuo **Token JWT**.
* **Autenticazione delle richieste:** Copia il token JWT ricevuto dal login. Nelle successive richieste protette su Postman, recati nella scheda **Authorization**, seleziona la tipologia **Bearer Token** e incolla il token nel campo apposito.

---

## 📡 Endpoint Principali

La seguente lista riassume le rotte esposte dall'applicazione:

### Autenticazione (Pubblici)
* `POST /auth/signup` : Registrazione di un nuovo profilo.
* `POST /auth/login` : Autenticazione e generazione del token JWT.

### Eventi (Protetti)
* `GET /events` : Recupero della lista di tutti gli eventi disponibili.
* `DELETE /events/me/d/{eventId}` : Eliminazione di un evento (operazione consentita solo all'organizzatore che lo ha creato).

### Prenotazioni (Protetti)
* `GET /bookings` : Recupero delle informazioni sulle prenotazioni.
* `POST /bookings/{eventId}` : Creazione di una nuova prenotazione per un determinato evento.
* `PATCH /bookings/{bookingId}` : Annullamento o aggiornamento di una prenotazione esistente.

### Area Personale (Protetti)
* `GET /users/me/events` : Visualizzazione di tutti gli eventi generati dall'organizzatore attualmente autenticato.
* `GET /users/me/bookings` : Visualizzazione dello storico delle prenotazioni dell'utente attualmente autenticato.