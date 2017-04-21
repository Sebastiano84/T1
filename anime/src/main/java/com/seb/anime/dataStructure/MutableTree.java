package com.seb.anime.dataStructure;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public interface MutableTree<N extends Serializable> extends Iterator<MutableTree<N>>{
	boolean add(N parent, N node);

	boolean remove(N node);

	List<MutableTree<N>> getChildren();

	MutableTree<N> getParent();

	N getValue();

	void resetIterator(boolean b);
	
	int getLevel();
}
