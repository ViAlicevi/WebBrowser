import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Account{
	public void start(Stage stage)

{

	// set title for the stage
	stage.setTitle("Creating popup");

	// create a button
	Button button = new Button("Signin");

	// create a tile pane, ngan xep
	TilePane tilepane = new TilePane();

	// create a label
	Label label = new Label("This is a Popup");
	TextField tfEmail = new TextField("email");
	PasswordField tfpw = new PasswordField();
	
	tfEmail.setOnKeyPressed(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
//			if(tfEmail.getText().isEmpty()) {
//				button.setDisable(false);
//			}
			if(!tfEmail.getText().contains("@gmail.com")) {
				button.setDisable(false);
			}
			else
				button.setDisable(true);
			
		}
	});
	tfpw.setOnKeyPressed(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
//			if(tfEmail.getText().isEmpty()) {
//				button.setDisable(false);
//			}
			if(!tfEmail.getText().contains("@gmail.com")) {
				button.setDisable(false);
			}
			else
				button.setDisable(true);
			
		}
	});

	// create a popup
//	Popup popup = new Popup();

	// set background
	label.setStyle(" -fx-background-color: white;");

	// add the label
//	popup.getContent().add(label);
//	popup.getContent().add(tfEmail);
//	popup.getContent().add(tfpw);
//	popup.getContent().addAll(label, tfEmail, tfpw);

	// set size of label
//	label.setMinWidth(150);
//	label.setMinHeight(150);
//	tfEmail.setMinWidth(150);
//	tfEmail.setMinHeight(150);
//	tfpw.setMinWidth(150);
//	tfpw.setMinHeight(150);

	// action event
//	EventHandler<ActionEvent> event =
//	new EventHandler<ActionEvent>() {
//
//		public void handle(ActionEvent e)
//		{
//			if (!popup.isShowing())
//				popup.show(stage);
//			else
//				popup.hide();
//		}
//	};

	// when button is pressed
	//button.setOnAction(event);

	// add button
	tilepane.getChildren().addAll(tfEmail, tfpw, button);

	// create a scene
	Scene scene = new Scene(tilepane, 500, 500);

	// set the scene	
	stage.setScene(scene);

	stage.show();
	}

	public Object Account() {
		// TODO Auto-generated method stub
		return null;
	}
	
}