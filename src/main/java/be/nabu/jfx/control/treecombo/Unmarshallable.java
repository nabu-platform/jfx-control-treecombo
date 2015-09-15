package be.nabu.jfx.control.treecombo;

import be.nabu.jfx.control.tree.Marshallable;

public interface Unmarshallable<T> extends Marshallable<T> {
	public T unmarshal(String value);
}
