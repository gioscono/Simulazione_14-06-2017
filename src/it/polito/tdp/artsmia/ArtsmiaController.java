/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {

	private Model model;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {

    	int anno =0;
    	anno = boxAnno.getValue();
    	boolean connesso = model.creaGrafo(anno);
    	if(connesso==true){
    		txtResult.appendText("Il grafo creato è connesso.\n");
    	}else{
    		txtResult.appendText("Il grafo creato non è connesso.\n");
    	}
    	Exhibition e = model.calcolaMaggiorNumeroOpere();
    	txtResult.appendText("Esibizione con maggior numero di opere: "+e.toString()+" con "+e.getOggetti().size()+" opere.\n");
    	
    }

    @FXML
    void handleSimula(ActionEvent event) {

    	String numeroS = txtFieldStudenti.getText();
    	
    	try{
    		int i = Integer.parseInt(numeroS);
    		int anno = boxAnno.getValue();
    		List<Studente> studenti = model.simula(anno, i);
    		for(Studente s : studenti){
    			txtResult.appendText(s.getMat()+"-"+s.getMostreVisitate().size()+"\n");
    		}
    	}catch(NumberFormatException e){
    		txtResult.appendText("Errore: inserire un numero.\n");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getAllYears());
		boxAnno.setValue(boxAnno.getItems().get(0));
	}
}
