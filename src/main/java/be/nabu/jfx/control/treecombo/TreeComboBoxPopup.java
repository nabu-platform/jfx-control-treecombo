package be.nabu.jfx.control.treecombo;

import be.nabu.jfx.control.tree.Tree;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TreeComboBoxPopup<T> {
	
	private Tree<T> tree;

	public TreeComboBoxPopup(Tree<T> tree) {
		this.tree = tree;
	}

	public Pane build() {
		AnchorPane pane = new AnchorPane();
		pane.getChildren().add(tree);
		AnchorPane.setBottomAnchor(tree, 0d);
		AnchorPane.setTopAnchor(tree, 0d);
		AnchorPane.setLeftAnchor(tree, 0d);
		AnchorPane.setRightAnchor(tree, 0d);
		return pane;
	}
}
