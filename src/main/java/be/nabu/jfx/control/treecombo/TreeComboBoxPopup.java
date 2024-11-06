/*
* Copyright (C) 2015 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
