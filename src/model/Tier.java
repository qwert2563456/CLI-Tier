package model;

public class Tier {
	private Board board;
	private int id;
	private int sortOrder;
	private String name;

	public Tier(Board board, int sortOrder, String name) {
		this.board = board;
		this.sortOrder = sortOrder;
		this.name = name;
	}
	
	public Tier(Board board, int id, int sortOrder, String name) {
		this(board, sortOrder, name);
		setId(id);
	}

	public String getName() {
		return name;
	}
	
	public int getSortOrder() {
		return sortOrder;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Board getBoard() {
		return board;
	}

	public int getId() {
		return id;
	}
}
