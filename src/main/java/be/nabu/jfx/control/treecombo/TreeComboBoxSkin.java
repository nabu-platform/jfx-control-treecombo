package be.nabu.jfx.control.treecombo;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TreeComboBoxSkin<T> extends SkinBase<TreeComboBox<T>> {

	public TreeComboBoxSkin(TreeComboBox<T> arg0) {
		super(arg0);
		initialize();
	} 

	private void initialize() {
		getChildren().clear();
		HBox hbox = new HBox();
		hbox.getChildren().add(getSkinnable().getTextField());
		getSkinnable().getButton().getStyleClass().add("jfx-treecombo-button");
		getSkinnable().getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				// horrible hack to make sure the context menu resizes properly
				getSkinnable().getTextField().getContextMenu().show(getSkinnable().getTextField(), Side.BOTTOM, 0, 0);
				getSkinnable().getTextField().getContextMenu().hide();
				getSkinnable().getTextField().getContextMenu().show(getSkinnable().getTextField(), Side.BOTTOM, 0, 0);
				if (getSkinnable().getTree().getSelectionModel().getSelectedItem() != null) {
					getSkinnable().getTree().getSelectionModel().getSelectedItem().getNode().requestFocus();
				}
				else {
					getSkinnable().getTree().getTreeCell(getSkinnable().getTree().rootProperty().get()).getNode().requestFocus();
				}
			}
		});
		hbox.getChildren().add(getSkinnable().getButton());
		getChildren().add(hbox);
	}
}
