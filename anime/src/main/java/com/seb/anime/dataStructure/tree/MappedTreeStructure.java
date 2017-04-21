package com.seb.anime.dataStructure.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import org.springframework.util.Assert;

import com.seb.anime.dataStructure.MutableTree;

public class MappedTreeStructure<N extends Serializable> implements MutableTree<N> {

	/**
	 * 
	 */
	public static final long serialVersionUID = -3967668092061104006L;
	private MutableTree<N> parent;
	private List<MutableTree<N>> children;
	private N value;
	private int iteratorIndex;

	public MappedTreeStructure(N value) {
		this(null, value);
	}

	public MappedTreeStructure(MutableTree<N> parent, N value) {
		this(parent, new ArrayList<MutableTree<N>>(), value);
	}

	public MappedTreeStructure(MutableTree<N> parent, List<MutableTree<N>> children, N value) {
		super();
		Assert.notNull(children);
		this.parent = parent;
		this.children = children;
		this.value = value;
		iteratorIndex = -1;
	}

	public MutableTree<N> getParent() {
		return parent;
	}

	public N getValue() {
		return value;
	}

	public List<MutableTree<N>> getChildren() {
		return children;
	}

	@Override
	public boolean add(final N parent, final N node) {
		if (value.equals(parent))
			return children.add(new MappedTreeStructure<N>(this, node));
		final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		children.forEach(new Consumer<MutableTree<N>>() {
			@Override
			public void accept(MutableTree<N> t) {
				boolean b = atomicBoolean.get();
				if (!b)
					atomicBoolean.lazySet(b || t.add(parent, node));

			}
		});
		return atomicBoolean.get();
	}

	@Override
	public boolean remove(final N node) {
		if (value.equals(node)) {
			return parent.remove(node);
		}
		Iterator<MutableTree<N>> iterator = children.iterator();
		boolean found = false;
		while (iterator.hasNext() && !found) {
			MutableTree<N> next = iterator.next();
			if (next.getValue().equals(node)) {
				iterator.remove();
				found = true;
			}
		}
		return found;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("\n");
		for (int i = 0; i < getLevel(); i++)
			sb.append("    ");
		sb.append("- ").append(getLevel()).append(") ").append(getValue());
		children.forEach(new Consumer<MutableTree<N>>() {

			@Override
			public void accept(MutableTree<N> t) {
				sb.append(t);

			}
		});
		return sb.toString();
	}

	@Override
	public boolean hasNext() {
//		System.out.println(value + "\t" + iteratorIndex);
		if (iteratorIndex == -1)
			return true;
		if (iteratorIndex < children.size()) {
			boolean hasNext = children.get(iteratorIndex).hasNext();
			if (hasNext)
				return true;
			if ((iteratorIndex + 1) < children.size())
				return children.get(++iteratorIndex).hasNext();
		}
		return false;
	}

	@Override
	public MutableTree<N> next() {
		if (iteratorIndex == -1) {
			iteratorIndex++;
			return this;
		}
		return children.get(iteratorIndex).next();
	}

	public void resetIterator(boolean recursive) {
		iteratorIndex = -1;
		if(recursive){
			for (MutableTree<N> mutableTree : children) {
				mutableTree.resetIterator(recursive);
			}
		}
	}

	@Override
	public int getLevel() {
		int level = 0;
		MutableTree<N> current = this;
		while ((current = current.getParent()) != null)
			level++;
		return level;
	}
}