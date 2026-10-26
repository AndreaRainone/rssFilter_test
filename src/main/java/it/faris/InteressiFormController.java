package it.faris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class InteressiFormController {

    @FXML private VBox mainCard;
    @FXML private StackPane rightContentArea;

    // Tab di sinistra
    @FXML private ToggleButton tabInfo, tabTech, tabScienza, tabBusiness, tabCultura, tabGeek, tabLifestyle;

    // Set globale di tutti i tag selezionati dall'utente in qualsiasi categoria
    private final Set<String> tagSelezionati = new HashSet<>();
    // Mappa per memorizzare i pannelli delle categorie
    private final Map<String, VBox> categoryPanels = new HashMap<>();

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        // Gestione trascinamento finestra trasparente
        if (mainCard != null) {
            mainCard.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            mainCard.setOnMouseDragged(event -> {
                Node source = (Node) event.getSource();
                source.getScene().getWindow().setX(event.getScreenX() - xOffset);
                source.getScene().getWindow().setY(event.getScreenY() - yOffset);
            });
        }

        // Costruisce i pannelli di destra con la tassonomia completa
        buildCategoryPanels();

        // Mostra di default la prima categoria
        showPanel("INFO");
    }

    private void buildCategoryPanels() {
        // 1. Info & Attualità
        categoryPanels.put("INFO", createSectionView(Map.of(
                "Politica & Geopolitica", List.of("Politica Nazionale", "Politica Internazionale", "Analisi & Think Tank", "Istituzioni & Governi"),
                "Cronaca & Notizie", List.of("Ultima Ora", "Economia & Mercati", "Ambiente & Clima", "Società & Diritti"),
                "Inchieste & Reportage", List.of("Giornalismo Investigativo", "Data Journalism", "Documentari & Podcast")
        )));

        // 2. Tecnologia & Code
        categoryPanels.put("TECH", createSectionView(Map.of(
                "Informatica & Software", List.of("Sviluppo Web & Coding", "Open Source & Linux", "Cybersecurity", "Java & JVM", "Architettura Esagonale"),
                "Tecnologie Emergenti", List.of("Intelligenza Artificiale", "Blockchain & Crypto", "Robotica", "Web3"),
                "Systems & Code", List.of("Linguaggio Rust", "C & C++ Systems", "Linux Kernel & OS", "Embedded Systems")
        )));

        // 3. Scienza & Spazio
        categoryPanels.put("SCIENZA", createSectionView(Map.of(
                "Scienze Esatte", List.of("Fisica & Matematica", "Astronomia & Spazio", "Chimica"),
                "Scienze della Vita", List.of("Medicina & Salute", "Biologia & Genetica", "Neuroscienze"),
                "Terra & Storia", List.of("Geologia", "Paleontologia", "Archeologia")
        )));

        // 4. Business & Finanza
        categoryPanels.put("BUSINESS", createSectionView(Map.of(
                "Mercati Finanziari", List.of("Borsa & Azioni", "Obbligazioni & ETF", "Materie Prime"),
                "Impresa & Startup", List.of("Imprenditoria", "Venture Capital", "Marketing & Growth"),
                "Finanza Personale", List.of("Risparmio & Investimenti", "Tasse & Fisco", "Real Estate")
        )));

        // 5. Cultura & Società
        categoryPanels.put("CULTURA", createSectionView(Map.of(
                "Arte & Letteratura", List.of("Recensioni Libri", "Fumetti & Manga", "Sci-Fi & Fantastica"),
                "Intrattenimento & Media", List.of("Cinema & Serie TV", "Anime & Animazione", "Streaming & Creator Economy"),
                "Umanistica & Pensiero", List.of("Filosofia", "Storia", "Antropologia & Psicologia", "Etica della Tecnologia")
        )));

        // 6. Geek & Community
        categoryPanels.put("GEEK", createSectionView(Map.of(
                "Nerd & Pop Culture", List.of("Giochi di Ruolo (D&D)", "Board Games & TTRPG", "Retro Gaming", "Indie Game Dev", "Gaming & eSports"),
                "Maker & DIY", List.of("Domotica & Home Assistant", "Raspberry Pi & Arduino", "Stampa 3D", "Retrocomputing"),
                "Net Culture & Privacy", List.of("Crittografia & Tor", "Open Web & Fediverso", "Forum & Community")
        )));

        // 7. Lifestyle & Sport
        categoryPanels.put("LIFESTYLE", createSectionView(Map.of(
                "Salute & Benessere", List.of("Fitness & Allenamento", "Nutrizione & Dieta", "Mental Health", "Biohacking"),
                "Tempo Libero", List.of("Viaggi & Esplorazioni", "Fotografia", "Birra Artigianale & Food"),
                "Sport & Motori", List.of("Calcio", "Formula 1", "MotoGP", "Tennis & Padel", "Ciclismo")
        )));
    }

    private VBox createSectionView(Map<String, List<String>> sections) {
        VBox container = new VBox(15);
        for (var entry : sections.entrySet()) {
            VBox group = new VBox(6);
            Label title = new Label(entry.getKey());
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: #991845;");

            FlowPane flow = new FlowPane(8, 8);
            for (String tagName : entry.getValue()) {
                ToggleButton chip = new ToggleButton(tagName);
                chip.getStyleClass().add("interest-chip-compact");

                // Ripristina lo stato se il tag era già stato selezionato
                if (tagSelezionati.contains(tagName)) {
                    chip.setSelected(true);
                }

                chip.setOnAction(e -> {
                    if (chip.isSelected()) {
                        tagSelezionati.add(tagName);
                    } else {
                        tagSelezionati.remove(tagName);
                    }
                });
                flow.getChildren().add(chip);
            }
            group.getChildren().addAll(title, flow);
            container.getChildren().add(group);
        }
        return container;
    }

    @FXML
    private void switchCategory(ActionEvent event) {
        ToggleButton btn = (ToggleButton) event.getSource();

        tabInfo.setSelected(false);
        tabTech.setSelected(false);
        tabScienza.setSelected(false);
        tabBusiness.setSelected(false);
        tabCultura.setSelected(false);
        tabGeek.setSelected(false);
        tabLifestyle.setSelected(false);
        btn.setSelected(true);

        if (btn == tabInfo) showPanel("INFO");
        else if (btn == tabTech) showPanel("TECH");
        else if (btn == tabScienza) showPanel("SCIENZA");
        else if (btn == tabBusiness) showPanel("BUSINESS");
        else if (btn == tabCultura) showPanel("CULTURA");
        else if (btn == tabGeek) showPanel("GEEK");
        else if (btn == tabLifestyle) showPanel("LIFESTYLE");
    }

    private void showPanel(String key) {
        VBox panel = categoryPanels.get(key);
        if (panel != null) {
            rightContentArea.getChildren().setAll(panel);
        }
    }

    @FXML
    private void savePreferences() {
        System.out.println("Tag totali salvati per il filtraggio RSS: " + tagSelezionati);
        closeWindow();
    }

    @FXML
    private void closeWindow() {
        if (mainCard != null && mainCard.getScene() != null) {
            Stage stage = (Stage) mainCard.getScene().getWindow();
            stage.close();
        }
    }
}