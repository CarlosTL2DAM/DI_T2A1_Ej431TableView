/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di_t2_a1_ej43tableview;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author usuario
 */
public class DI_T2_A1_Ej43TableView extends Application {
    
    //Creamos la clase TableView
    private final TableView table = new TableView();
    //Incluimos los distintos datos en nuestra lista
    final ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johson@example.com"),
            new Person("Ethan", "JWilliams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jonse@exampl.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
    );
    final HBox hb = new HBox();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Creamos escena con un layout group, titulo y dimensiones
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Table View Sample");
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);
        
        //Incluimos una etiqueta, con nombre y fuente cambiados
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        
        //Permite editar la tabla
        table.setEditable(true);
        
        //El primer parámetro del callback hace una llamada, el segundo devuelve la respuesta.
        Callback<TableColumn<Person, String>,
                TableCell<Person,String>> cellFactory 
                =(TableColumn<Person, String> p) -> new EditingCell();
        
        //Creamos las columnas con sus titulos y le damos una anchura mínima
        TableColumn<Person, String> firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        //Enlazamos los datos en las columnas de la tabla con el método setCellValueFactory
        firstNameCol.setCellValueFactory(
            new PropertyValueFactory<>("firstName")
        );
        //Método para permitirnos modificar los datos directamente en la tabla
        //firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        firstNameCol.setCellFactory(cellFactory);
        firstNameCol.setOnEditCommit(
        (CellEditEvent<Person,String>t) ->{
            ((Person) t.getTableView().getItems().get(
            t.getTablePosition().getRow())
            ).setFirstName(t.getNewValue());
        });
        
        TableColumn<Person, String> lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        //Enlazamos los datos en las columnas de la tabla con el método setCellValueFactory
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        //Método para permitirnos modificar los datos directamente en la tabla
        //lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        lastNameCol.setCellFactory(cellFactory);
        lastNameCol.setOnEditCommit(
        (CellEditEvent<Person,String>t) ->{
            ((Person) t.getTableView().getItems().get(
            t.getTablePosition().getRow())
            ).setFirstName(t.getNewValue());
        });
        
        TableColumn<Person, String> emailCol = new TableColumn("Email");
        emailCol.setMinWidth(100);
        //Enlazamos los datos en las columnas de la tabla con el método setCellValueFactory
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );
        //Método para permitirnos modificar los datos directamente en la tabla
        //emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        emailCol.setCellFactory(cellFactory);
        emailCol.setOnEditCommit(
        (CellEditEvent<Person,String>t) ->{
            ((Person) t.getTableView().getItems().get(
           t.getTablePosition().getRow())
            ).setFirstName(t.getNewValue());
        });
        
        //Insertamos los datos en las columnas, anteriormente declaramos donde irá cada una con setCellValueFactory
        table.setItems(data);
        
        //Añadimos las columnas
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        //Creamos subcolumnas para emailCol
        //TableColumn firstEmailCol = new TableColumn("Primary");
        //TableColumn secondEmailCol = new TableColumn("Secundary");
        //Añadimso las subcolumnas a la columna emailCol
        //emailCol.getColumns().addAll(firstEmailCol, secondEmailCol);

        //Creamos TextFields para incluir nuevos valores para las columnas.
        //TextField para FirstName, incluimos un pequeño texto que al pulsar se borrará y su anchura máxima
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        
        //TextField para LastName, incluimos un pequeño texto que al pulsar se borrará y su anchura máxima
        final TextField addLastName = new TextField();
        addLastName.setPromptText("Last Name");
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        
        //TextField para email, incluimos un pequeño texto que al pulsar se borrará y su anchura máxima
        final TextField addEmail = new TextField();
        addEmail.setPromptText("Email");
        addEmail.setMaxWidth(firstNameCol.getPrefWidth());
        
        //Incluimos botón 
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) ->{
            //Añadimos los tres valores introducidos
            data.add(new Person(
                addFirstName.getText(),
                addLastName.getText(),
                addEmail.getText()
            ));
            //Limpiamos los tres textFields
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });
        
        //Incluimos en un HBox los distintos componenentes que acabamos de añadir
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
        
        //Añadimos un VBox con un espacio de 5 y padding, aqui incluiremos tanto la label como la tabla
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label,table,hb);
        
        //Hace un casting de scene.getRoot que obtiene el root de la escena
        //Posteriormente obtiene los hijos de este root y le añade vbox
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        //Incluimos la escena y mostramos la primaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class Person {
    //La clase SimpleStringProperty proporciona todas las propiedades 
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    
    protected Person(String fName, String lName, String email)
    {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.email = new SimpleStringProperty(email);
    }
    
    /*Métodos Gets y sets*/
    public String getFirstName(){
        return firstName.get();
    }
    
    public void setFirstName(String fName)
    {
        firstName.set(fName);
    }
    
    public String getLastName(){
        return lastName.get();
    }
    
    public void setLastName(String lName)
    {
        lastName.set(lName);
    }
    
    public String getEmail(){
        return email.get();
    }
    
    public void setEmail(String email)
    {
        firstName.set(email);
    }
    
}
    
    
    //Método para editar celdas, extiende de TableCell

    class EditingCell extends TableCell<Person, String> {
        private TextField textField;
        
        public EditingCell(){
        }
        
        @Override
        public void startEdit(){
            //En caso de estar vacio
            if(!isEmpty()){
                //Permite que se pueda editar
                super.startEdit();
                //Llama al método createTextField, declarado abajo
                createTextField();
                //Pone el texto a nulo
                setText(null);
                //Proporciona propiedades gráficas del tipo textField
                setGraphic(textField);
                //Selecciona todo el texto en la entrada de texto
                textField.selectAll();
            }
        }
        
        @Override
        public void cancelEdit() {
            //Impide que se pueda editar
            super.cancelEdit();
            //Escribe el item como texto
            setText((String) getItem());
            //Proporciona propiedades gráficas que no tenga
            setGraphic(null);
        }
        
        @Override
        public void updateItem(String item, boolean empty) {
            //Actualiza el item a vacio
            super.updateItem(item, empty);
            //En caso de que este vacio pone el texto y que se muestre  a vacio
            if(empty) {
                //Quita el texto
                setText(null);
                //Proporciona propiedades gráficas que no tenga

                setGraphic(null);
            } 
            //En caso de que no esta vacio, lo edita
            else {
                if(isEditing()) {
                    //Si el textField lo pone a nulo
                    if(textField != null) {
                        textField.setText(getString());
                    }
                    //Añade el setGraphic con formato de TextField
                    setText(null);
                    setGraphic(textField);
                } 
                //Añade el texto si no es nulo
                else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
        
        private void createTextField() {
            //Crea un TextField
            textField = new TextField(getString());
            //Le añade dimensiones
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            //Le añade un evento si esta focuseado
            textField.focusedProperty().addListener(
            (ObservableValue<? extends Boolean> arg0,
            Boolean arg1, Boolean arg2) -> {
                if(!arg2){
                    //Guarda los cambios editados en la clave
                    commitEdit(textField.getText());
                }
            });
        }
        
        private String getString() {
            //Devuelve "" si es nulo, si no está nulo, devuelve el string del item.
            return getItem() == null ? "": getItem().toString();
        }
    }
}
