package model;

public class Item {
	private int id;
	private Tier tier;
	private Board board;
	private String name;
	private int position;
	private String comment;

	public Item(String name, Board board) {
		setName(name);
		setBoard(board);
	}

	public Item(int id, Tier tier, Board board, String name, int position, String comment) {
		this(name, board);
		this.id = id;
		this.tier = tier;
		this.position = position;
		this.comment = comment;
	}

	public Board getBoard() {
		return board;
	}

	public String getName() {
		return name;
	}

	public Tier getTier() {
		return tier;
	}

	public int getPosition() {
		return position;
	}

	public String getComment() {
		return comment;
	}

	public int getId() {
		return id;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
