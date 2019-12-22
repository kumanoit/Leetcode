package com.kumanoit.tree;

import java.util.List;

public class NaryTreeNode {
	int val;
	List<NaryTreeNode> children;

	public NaryTreeNode(int val) {
		this.val = val;
	}

	public NaryTreeNode(int val, List<NaryTreeNode> children) {
		this.val = val;
		this.children = children;
	}
}