package be.nabu.jfx.control.treecombo;

import be.nabu.jfx.control.tree.StringMarshallable;

public class StringUnmarshallable extends StringMarshallable implements Unmarshallable<String> {

	@Override
	public String unmarshal(String value) {
		return value;
	}

}
