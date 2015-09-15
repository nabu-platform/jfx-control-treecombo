package be.nabu.jfx.control.treecombo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import be.nabu.jfx.control.tree.Marshallable;
import be.nabu.jfx.control.tree.Tree;
import be.nabu.jfx.control.tree.TreeCell;
import be.nabu.jfx.control.tree.TreeItem;

public class TreeComboBox<T> extends Control {

	private Tree<T> tree;
	private TextField textField;
	private TreeComboBoxSelectionModel<T> selectionModel;
	private boolean isControlledUpdate;
	private boolean closeOnSelect = true;
	private Button button;
	
	public TreeComboBox(Tree<T> tree, final Marshallable<T> selectionMarshaller) {
		getStyleClass().add("jfx-treecombo");
		this.tree = tree;
		this.tree.getSelectionModel().selectionModeProperty().set(SelectionMode.SINGLE);
		this.textField = new TextField();
		this.button = new Button();
		this.textField.setEditable(false);
		this.textField.setContextMenu(buildContextMenu(tree));
		this.selectionModel = new TreeComboBoxSelectionModel<T>(tree.getSelectionModel());
		// select root if any
		if (this.tree.rootProperty().isNotNull().get()) {
			tree.getSelectionModel().select(tree.getTreeCell(tree.rootProperty().get()));
		}
		// if the root changes, select it again for visual reasons
		this.tree.rootProperty().addListener(new ChangeListener<TreeItem<T>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<T>> arg0, TreeItem<T> arg1, TreeItem<T> arg2) {
				tree.getSelectionModel().select(tree.getTreeCell(arg2));
			}
		});
		this.tree.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1 && tree.getSelectionModel().getSelectedItem().getItem().leafProperty().get()) {
					selectCurrent(tree, selectionMarshaller);
					event.consume();
				}
			}
		});
		this.tree.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER && tree.getSelectionModel().getSelectedItem().getItem().leafProperty().get()) {
					selectCurrent(tree, selectionMarshaller);
					event.consume();
				}
			}
		});
		this.textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// update the selection manually
				if (!isControlledUpdate && selectionMarshaller instanceof Unmarshallable) {
					if (arg2 == null || arg2.isEmpty()) {
						selectionModel.clearSelection();
					}
					else {
						selectionModel.select(((Unmarshallable<T>) selectionMarshaller).unmarshal(arg2));
					}
				}
			}
		});
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> arg0, T arg1, T arg2) {
				if (!isControlledUpdate) {
					if (arg2 == null) {
						textField.setText(null);
					}
					else {
						textField.setText(selectionMarshaller.marshal(arg2));
					}
				}
			}
		});
	}
	
	private void selectCurrent(Tree<T> tree, final Marshallable<T> selectionMarshaller) {
		isControlledUpdate = true;
		textField.setText(selectionMarshaller.marshal(tree.getSelectionModel().getSelectedItem().getItem().itemProperty().get()));
		// hide the popup
		if (closeOnSelect) {
			textField.getContextMenu().hide();
		}
		selectionModel.select(tree.getSelectionModel().getSelectedItem().getItem());
		isControlledUpdate = false;
	}
	
	public Tree<T> getTree() {
		return tree;
	}
	
	public TextField getTextField() {
		return textField;
	}
	
	private ContextMenu buildContextMenu(final Tree<T> tree) {
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!tree.equals(event.getTarget())) {
					tree.fireEvent(event);
				}
			}
		});
		CustomMenuItem menuItem = new CustomMenuItem();
		menuItem.setContent(new TreeComboBoxPopup<T>(tree).build());
		contextMenu.getItems().add(menuItem);
		contextMenu.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (arg2) {
					// preselect the treecell (if any)
					if (getSelectionModel().getSelectedTreeItem() != null) {
						TreeCell<T> treeCell = tree.getTreeCell(getSelectionModel().getSelectedTreeItem());
						if (treeCell != null) {
							// select it in the tree as well
							tree.getSelectionModel().select(treeCell);
							// open the hierarchy to the correct position
							treeCell.show();
						}
					}
					tree.resize();
				}
			}
		});
		return contextMenu;
	}
	
	public TreeComboBoxSelectionModel<T> getSelectionModel() {
		return selectionModel;
	}
	
	@Override
	protected String getUserAgentStylesheet() {
		return Tree.class.getClassLoader().getResource("jfx-treecombo.css").toExternalForm();
	}

	public boolean isCloseOnSelect() {
		return closeOnSelect;
	}

	public void setCloseOnSelect(boolean closeOnSelect) {
		this.closeOnSelect = closeOnSelect;
	}

	public Button getButton() {
		return button;
	}
}
