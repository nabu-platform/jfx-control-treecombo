package be.nabu.jfx.control.treecombo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import be.nabu.jfx.control.tree.StringMarshallable;
import be.nabu.jfx.control.tree.Tree;
import be.nabu.jfx.control.tree.helper.CategoryTreeItem;
import be.nabu.jfx.control.tree.helper.SimpleCategory;
import be.nabu.jfx.control.tree.helper.SimpleItem;
import be.nabu.jfx.control.treecombo.StringUnmarshallable;
import be.nabu.jfx.control.treecombo.TreeComboBox;

public class Test extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		VBox vbox = new VBox();
		
		Tree<String> tree = new Tree<String>(new StringMarshallable());
		
		SimpleCategory<String> category = new SimpleCategory<String>("root");
		
		category.add(
			new SimpleCategory<String>("category1").add(
				new SimpleItem<String>("item1"),
				new SimpleItem<String>("item2")
			),
			new SimpleCategory<String>("category2").add(
				new SimpleItem<String>("item3"),
				new SimpleItem<String>("item4")
			)
		);
		
		tree.rootProperty().set(new CategoryTreeItem<String>(category, new StringMarshallable()));
		final TreeComboBox<String> comboBox = new TreeComboBox<String>(tree, new StringUnmarshallable());
		comboBox.getTextField().editableProperty().set(true);
		vbox.getChildren().add(comboBox);
		root.setCenter(vbox);
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String... args) {
		launch(args);
	}
}
