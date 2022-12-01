import java.util.Base64;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class History extends Application {

    private TextField textField ;
    private WebView webView ;
    private Button btnZin, btnZout, btnhistory, backButton, forwardButton, btnAccount;
    private double webZoom;

    @Override
    public void start(Stage primaryStage) {
        webView = new WebView();
        webZoom = 1;
       

        webView.getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                primaryStage.setTitle(webView.getEngine().getTitle());
                textField.setText(webView.getEngine().getLocation());
            }
        });

        textField = new TextField("https://www.google.com/");
        textField.setOnAction(e -> load());

        load();

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> webView.getEngine().loadContent(""));

        WebHistory history = webView.getEngine().getHistory();

        backButton =  new Button("<");
        backButton.disableProperty().bind(history.currentIndexProperty().lessThanOrEqualTo(0));
        backButton.setOnAction(e -> 
                history.go(-1));

        forwardButton = new Button(">");
        forwardButton.disableProperty().bind(
                history.currentIndexProperty().greaterThanOrEqualTo(Bindings.size(history.getEntries()).subtract(1)));
        forwardButton.setOnAction(e -> 
                history.go(1));
        
        btnZin = new Button("-");
        btnZin.setOnAction(e ->
        	webView.setZoom(webZoom -= 0.25));
        
        btnZout = new Button("+");
        btnZout.setOnAction(e -> 
        	webView.setZoom(webZoom += 0.25));
        
        btnhistory = new Button("History");
        btnhistory.setOnAction(e -> displayHistory());
        displayHistory();
        
        Account account = new Account();
        btnAccount = new Button("Account");
       Stage stage = new Stage();
        btnAccount.setOnAction(e -> account.start( stage));
        
        
        
        TabPane tabpane = new TabPane();  

        BorderPane root = new BorderPane(tabpane, null, null, null, null);

        textField.setPadding(new Insets(5));
        root.setPadding(new Insets(5));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    //    final TabPane tabPane = new TabPane();  
        //Preferred Size of TabPane.  
        //Placement of TabPane.  
        tabpane.setSide(Side.TOP);  
        /* To disable closing of tabs.  
         * tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);*/  
        final Tab newtab = new Tab();  
        newtab.setText("+");  
       
        newtab.setClosable(false);  
        //Addition of New Tab to the tabpane. 
       
        tabpane.getTabs().addAll(newtab);  
        createAndSelectNewTab(tabpane, "        ");  
        //Function to add and display new tabs with default URL display.  
        tabpane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {  
             @Override  
             public void changed(ObservableValue<? extends Tab> observable,  
                       Tab oldSelectedTab, Tab newSelectedTab) {  
                  if (newSelectedTab == newtab) {  
                       Tab tab = new Tab();  
                       tab.setText("new tab");  
                       //WebView - to display, browse web pages.  
                       WebView webView = new WebView();  
                       final WebEngine webEngine = webView.getEngine();  
                       webEngine.load("https://www.google.com");  
                       final TextField urlField = new TextField("http://google.com"); 
                       
                       WebHistory history = webView.getEngine().getHistory();
                       backButton =  new Button("<");
                       backButton.disableProperty().bind(history.currentIndexProperty().lessThanOrEqualTo(0));
                       backButton.setOnAction(e -> 
                               history.go(-1));

                       forwardButton = new Button(">");
                       forwardButton.disableProperty().bind(
                               history.currentIndexProperty().greaterThanOrEqualTo(Bindings.size(history.getEntries()).subtract(1)));
                       forwardButton.setOnAction(e -> 
                               history.go(1));
                       
                       btnZin = new Button("-");
                       btnZin.setOnAction(e ->
                       	webView.setZoom(webZoom -= 0.25));
                       
                       btnZout = new Button("+");
                       btnZout.setOnAction(e -> 
                       	webView.setZoom(webZoom += 0.25));
                       
                       btnhistory = new Button("History");
                       btnhistory.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							 WebHistory history = webView.getEngine().getHistory();
 
					    	  ObservableList<WebHistory.Entry> entries = history.getEntries();
					        for(WebHistory.Entry entry: entries) {
					        	
					        	System.out.println(entry);
					        }
							
						}
                    	   
					});
                       
                       webEngine.locationProperty().addListener(new ChangeListener<String>() {  
                            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {  
                                 urlField.setText(newValue);  
                            }  
                       });  
                       //Action definition for the Button Go.  
                       EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {  
                            @Override public void handle(ActionEvent event) {  
                                 webEngine.load(urlField.getText().startsWith("http://")   
                                           ? urlField.getText()   
                                                     : "http://" + urlField.getText());  
                            }  
                       };  
                       urlField.setOnAction(goAction);  
                       // Layout logic  
                       HBox hBox = new HBox(5);  
                       hBox.getChildren().setAll(backButton, forwardButton, urlField, btnZin, btnZout, btnhistory);  
                       HBox.setHgrow(urlField, Priority.ALWAYS);  
                       final VBox vBox = new VBox(5);  
                       vBox.getChildren().setAll(hBox, webView);  
                       VBox.setVgrow(webView, Priority.ALWAYS);  
                       tab.setContent(vBox);  
                       final ObservableList<Tab> tabs = tabpane.getTabs();  
                       tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));  
                       tabs.add(tabs.size() - 1, tab);  
                       tabpane.getSelectionModel().select(tab);  
                  }  
             }  
             
        });   
    }
    
    private Tab createAndSelectNewTab(final TabPane tabPane, final String title) {  
        Tab tab = new Tab("new tab");   
        tab.setContent(webView);  
        
        final WebEngine webEngine = webView.getEngine();  
        webEngine.load("https://www.google.com");  
        final TextField urlField = new TextField("http://google.com");  
        webEngine.locationProperty().addListener(new ChangeListener<String>() {  
             @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {  
                  urlField.setText(newValue);  
             }  
        });  
        //Action definition for the Button Go.  
        EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {  
             @Override public void handle(ActionEvent event) {  
                  webEngine.load(urlField.getText().startsWith("http://")   
                            ? urlField.getText()   
                                      : "http://" + urlField.getText());  
             }  
        };  
        urlField.setOnAction(goAction);  
        HBox hBox = new HBox(5);  
        hBox.getChildren().setAll(backButton, forwardButton, urlField, btnZin, btnZout, btnhistory, btnAccount);  
        HBox.setHgrow(urlField, Priority.ALWAYS);  
        final VBox vBox = new VBox(5);  
        vBox.getChildren().setAll(hBox, webView);  
        VBox.setVgrow(webView, Priority.ALWAYS);  
        tab.setContent(vBox);  
        
        final ObservableList<Tab> tabs = tabPane.getTabs();  
        tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));  
        tabs.add(tabs.size() - 1, tab);  
        tabPane.getSelectionModel().select(tab);  
        return tab;  
   }  
    
    private void displayHistory() {
    	 WebHistory history = webView.getEngine().getHistory();

    	  ObservableList<WebHistory.Entry> entries = history.getEntries();
        for(WebHistory.Entry entry: entries) {
        	
        	System.out.println(entry);
        }
    }

    private void load() {
        String url = makeUrl(textField.getText()) ;
        webView.getEngine().load(url);
        textField.setText(url);
    }


    private String makeUrl(String text) {
        if (Pattern.matches("[a-zA-Z]+:.+", text)) {
            return text ;
        } else return "http://"+text ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
