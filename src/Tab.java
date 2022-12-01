//import javafx.beans.binding.Bindings;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Side;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//
//public class Tab { 
//	private WebView webView ;
//	TabPane tabpane = new TabPane();
//	
//
//     public Tab createAndSelectNewTab(final TabPane tabPane, final String title) { 
//    	 
//    	 webView = new WebView();
//         Tab tab = new Tab();   
//         tab.setContent(webView);  
//         
//         final ObservableList<javafx.scene.control.Tab> tabs = tabPane.getTabs();  
//         tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));  
//         tabs.add(tabs.size() - 1, tab);  
//         tabPane.getSelectionModel().select(tab);  
//         return tab;  
//    }  
//     
//
//}