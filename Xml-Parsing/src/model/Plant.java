package model;


import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("plant")
public class Plant {
	@XStreamAlias("tree")
	private List<Green> tree;
	@XStreamAlias("bush")
	private List<Green> bush;


	public List<Green> getTree() {
		return tree;
	}
	
	public List<Green> getBush() {
		return bush;
	}
	
	public void setTree(List<Green> tree) {
		this.tree = tree;
	}
	
	public void setBush(List<Green> bush) {
		this.bush = bush;
	}

	@Override
	public String toString() {
		return "Plant [tree=" + tree + ", bush=" + bush + "]";
	}	
}
