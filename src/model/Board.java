package model;

public class Board {
	private String name;
	private int id;

	public Board(String name) {
		this.name = name;
	}

	public Board(int id, String name) {
		this(name);
		setId(id);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
