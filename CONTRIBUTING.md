# **WORKFLOW Git & GitHub** - Linee guida all'organizzazione della repository.

Definizione del flusso di lavoro obbligatorio per tutti i membri del team.

*REGOLA: nessuno pusha direttamente sul branch 'main'. Lì finisce solo il codice già revisionato e approvato tramite Pull Request.*
----------------------------------------------------------------------------------------------------

# FLUSSO DI LAVORO:
## ***1. Sync dello spazio di lavoro***
**Prima di editare il codice, assicurarsi di avere l'ultima versione disponibile:**

```bash
git switch main
git pull origin main
```
> [!IMPORTANT]
>**Prima di editare SINCRONIZZARE repository online e locale, verificando prima i branch attivi**

```bash
git branch -a
```

<img width="499" height="99" alt="branch" src="https://github.com/user-attachments/assets/9bcc68a0-4e80-4dbf-83d3-45cfbd984481" />

**Subito sotto il comando ci sono i branch locali che avete attivi.**
**Subito dopo "main" ci sono i branch remoti attivi**

> [!WARNING]
> **SE la vostra precedente Pull Request è stata accettata e fatto il merge con il main, si deve sincronizzare la repository locale con quella online.**

**Cancellare il branch locale (l'eliminazione avviene solo se le modifiche del branch sono già state unite al main)**
```bash
git branch -d nomeBranch
```
**Dopodichè**

```bash
git fetch --prune
```
**Questo ultimo comando vi sincronizzerà la repository locale con quella online, cancellando i riferimenti a branch non più attivi**

## ***2. Creazione Branch di Lavoro***
**Per ogni Task e/o Sub-Task assegnato (a meno di blocchi contigui e correlati), lavorare su un branch dedicato.**

```bash
git switch -c nomeBranch
```

## ***3. Review e Pull Request***
**Prima di inviare le modifiche, posizionarsi nella cartella principale del progetto e poi fare:**

1. Prepara i file e crea il commit, inserendo il codice del ticket di Jira:

    Per aggiungere tutte le modifiche
    ```bash
    git add .
    ```
        
    Oppure per aggiungere un file specifico
    ```bash
    git add nomeFile.nomeEstensione
    ```
    Dopodichè
    ```bash
    git commit -m "CODICE_TASKJIRA Commento"
    ```
    Esempio: 
    ```bash
    git commit -m "ISFARIS-3 Aggiunto obiettivi del sistema"
    ```
    NOTA: se lavorate su più sottotask, per migliorare la tracciabilità, il messaggio del commit deve contenere tutti i codici dei sottotask di cui state facendo la pull request.
    ```bash
    git commit -m "ISFARIS-3 ISFARIS-4 ISFARIS-5 Aggiunto obiettivi del sistema"
    ```
    <mark>OPPURE un commit per ogni sottotask
    ```bash
    git commit -m "ISFARIS-3 Aggiunta sezione 1.1"
    git commit -m "ISFARIS-4 Aggiunta sezione 1.2"
    git commit -m "ISFARIS-5 Aggiunta sezione 1.3"
    ```
        
    Invio al branch su github
    ```bash
    git push -u origin nomeBranch
    ```

2. Entrare poi sulla repository di github, aprire una pull request e assegnare un "Reviewer" (Andrea / Davide) diverso dall'autore. Il titolo deve matchare con i codici di Jira dei vari subtask, simile a quello del commit con messaggio unico (ndr).

<mark>E' importante scrivere un messaggio di descrizione nella pull request</mark>

3. Scrivere nella sezione "Comunicazioni" di Discord, taggando il revisore e avvertendolo di revisionare quel task

## ***4. Nota per i revisori***
1. **Lasciare quantomeno un piccolo commento durante la review, che sia accettata/rifiutata/richiesta la modifica.**
2. **Dopo aver accettato la pull request e fatto il merge, è <mark>OBBLIGATORIO</mark>  cancellare il branch che l'ha creato**


## ***5. Altri comandi utili per gestire i branch***

Muoversi tra i branch
```bash
git switch nomeBranch
```

per annullare le modifiche al file e riportarlo all'ultimo commit
```bash
git restore nomeFile.estensione
```

per ripristinare tutti i file modificati nella cartella corrente
```bash
git restore . 
```
----------------------------------------------------------------------------------------------------

***Nota a margine***: 
dopo essere stato revisionato il merge sarà fatto da chi lo ha revisionato, ma risulterà comunque richiesto dal membro del team originale, quindi la traccia del lavoro svolto rimarrà.

    
