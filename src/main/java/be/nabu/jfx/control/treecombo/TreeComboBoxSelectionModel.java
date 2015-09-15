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
