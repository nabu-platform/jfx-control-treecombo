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

import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionModel;
import be.nabu.jfx.control.tree.TreeCell;
import be.nabu.jfx.control.tree.TreeItem;

public class TreeComboBoxSelectionModel<T> extends SelectionModel<T> {

	private MultipleSelectionModel<TreeCell<T>> tree;
	private TreeItem<T> selectedTreeItem;

	public TreeComboBoxSelectionModel(MultipleSelectionModel<TreeCell<T>> tree) {
		this.tree = tree;
	}
	
	@Override
	public void selectNext() {
		tree.selectNext();
	}

	@Override
	public void selectPrevious() {
		tree.selectPrevious();
	}

	@Override
	public void clearAndSelect(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clearSelection() {
		selectedTreeItem = null;
		super.setSelectedItem(null);
	}

	@Override
	public void clearSelection(int arg0) {
		clearSelection();
	}

	@Override
	public boolean isEmpty() {
		return super.getSelectedItem() == null;
	}

	@Override
	public boolean isSelected(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void select(int arg0) {
		throw new UnsupportedOperationException();
	}

	public void select(TreeItem<T> selected) {
		super.setSelectedItem(selected.itemProperty().get());
		this.selectedTreeItem = selected;
	}
	
	@Override
	public void select(T selected) {
		this.selectedTreeItem = null;
		super.setSelectedItem(selected);
	}

	@Override
	public void selectFirst() {
		tree.selectFirst();
	}

	@Override
	public void selectLast() {
		tree.selectLast();
	}

	public TreeItem<T> getSelectedTreeItem() {
		return selectedTreeItem;
	}
}
