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
<mark> **Prima di editare SINCRONIZZARE repository online e locale, verificando i branch attivi**

```bash
git branch -a
```

![branch](https://ibb.co/ddXBNR5)

```bash
git fetch
git fetch --prune
```


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
    <mark>Esempio: 
    ```bash
    git commit -m "ISFARIS-3 Aggiunto obiettivi del sistema"
    ```
    <mark>NOTA: se lavorate su più sottotask, per migliorare la tracciabilità, il messaggio del commit deve contenere tutti i codici dei sottotask di cui state facendo la pull request.
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

## ***4. Altri comandi utili per gestire i branch***

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
dopo essere stato revisionato il push sarà fatto da chi lo ha revisionato, ma risulterà comunque richiesto dal membro del team originale, quindi la traccia del lavoro svolto rimarrà.

    
