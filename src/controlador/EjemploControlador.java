package controlador;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Collections;


import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.control.textfield.AutoCompletionBinding;



public class EjemploControlador implements Initializable {

    @FXML
    private Button btnBoton;
    @FXML
    private ComboBox<String> selectorPaisFrom;
    @FXML
    private ComboBox<String> selectorPaisTo;
    @FXML
    private TextField cantidadFrom;
    @FXML
    private TextField cantidadTo;
    @FXML
    private ImageView intercambiarSeleccion;

    String codigoFrom;
    String codigoTo;
    Double cantidadAConvertir = 0.0;
    boolean seleccionadoDosPaises = false;
    String resultadoConversion = "";

    CalcularIntercambio calculoIntercambio = new CalcularIntercambio();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println(calculoIntercambio.calcular(10000,"ARS","CLP"));



        intercambiarSeleccion.setOnMouseClicked(event -> {
            String seleccionTo;
            seleccionTo = codigoTo;

            selectorPaisTo.setValue(codigoFrom);
            selectorPaisFrom.setValue(seleccionTo);
        });




        cantidadFrom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                cantidadFrom.setText(oldValue);
            } else {
                try {
                    cantidadAConvertir = Double.parseDouble(newValue);
                    if (seleccionadoDosPaises) {
                        resultadoConversion = Double.toString((calculoIntercambio.calcular(cantidadAConvertir, codigoFrom, codigoTo)));
                        cantidadTo.setText(resultadoConversion);
                    }
                    //System.out.println("El nuevo valor del TextFieldFrom es: " + newValue);
                } catch (NumberFormatException e) {
                    //System.out.println("Solo se deben ingresar numeros!");
                }
            }
        });

        cantidadTo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                cantidadFrom.setText(oldValue);
            }
            //System.out.println("El nuevo valor del TextFieldTo es: " + newValue);
        });


        Set<String> keys = Collections.emptySet(); // Inicializar con un conjunto vacío

        ConsumoAPI datos = ConsumoAPI.getInstance();
        Map<String, Double> rates = datos.getCurrencyRates();
        keys = rates.keySet();
        System.out.println(keys);

        ObservableList<String> keysList = FXCollections.observableArrayList(keys);
        selectorPaisFrom.setItems(keysList);
        selectorPaisTo.setItems(keysList);

        // Configurar la función de autocompletado para el ComboBox
        AutoCompletionBinding<String> autoCompletionBindingFrom = TextFields.bindAutoCompletion(selectorPaisFrom.getEditor(), keysList);
        AutoCompletionBinding<String> autoCompletionBindingTo = TextFields.bindAutoCompletion(selectorPaisTo.getEditor(), keysList);

        autoCompletionBindingFrom.setVisibleRowCount(6); // Limita el número de filas visibles
        autoCompletionBindingTo.setVisibleRowCount(6);

        // Este bloque permite que el texto ingresado por el usuario determine la selección,
        // pero no completa automáticamente el texto del usuario.
        autoCompletionBindingFrom.setOnAutoCompleted(event -> {
            //System.out.println("Auto-completado con: " + event.getCompletion());
        });

        // Agregar un listener para ocultar el menú desplegable cuando el usuario empiece a escribir
        selectorPaisFrom.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                selectorPaisFrom.hide();
            }
        });

        // Agregar un listener para ocultar el menú desplegable cuando el usuario empiece a escribir
        selectorPaisTo.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                selectorPaisTo.hide();
            }
        });

    }



    /* Funcion para colocar banderas... descartada
    private Callback<ListView<String>, ListCell<String>> createCellFactory() {
        return new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    private final ImageView imageView = new ImageView();

                    {
                        setContentDisplay(ContentDisplay.LEFT);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        System.out.println(item);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try{
                                Image img = new Image(getClass().getResourceAsStream("../imagenes/" + item + ".png"));
                                imageView.setImage(img);
                                imageView.setFitWidth(20); // Ajusta el ancho de la imagen
                                imageView.setFitHeight(20); // Ajusta la altura de la imagen
                                setGraphic(imageView);
                                setText(item);
                            } catch (NullPointerException e){
                                Image img = new Image(getClass().getResourceAsStream("../imagenes/CLP.png"));
                                imageView.setImage(img);
                                imageView.setFitWidth(20); // Ajusta el ancho de la imagen
                                imageView.setFitHeight(20); // Ajusta la altura de la imagen
                                setGraphic(imageView);
                                setText(item);
                            }

                        }
                    }
                };
            }
        };
    }*/




    @FXML
    private void handleComboBoxSelection(ActionEvent event) {
        String selectedValueFrom = selectorPaisFrom.getSelectionModel().getSelectedItem();
        String selectedValueTo = selectorPaisTo.getSelectionModel().getSelectedItem();
        codigoFrom = selectedValueFrom;
        codigoTo = selectedValueTo;
        if (codigoFrom != null && codigoTo !=null) {
            seleccionadoDosPaises = true;
            resultadoConversion = Double.toString((calculoIntercambio.calcular(cantidadAConvertir,codigoFrom,codigoTo)));
            cantidadTo.setText(resultadoConversion);
        }

        
    }
}
