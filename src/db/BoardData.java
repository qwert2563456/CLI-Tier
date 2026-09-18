package db;

import java.util.List;

import model.Board;
import model.Item;
import model.Tier;

public class BoardData {

	private Board board;
	private List<Tier> tiers;
	private List<Item> items;

	public BoardData(Board board, List<Tier> tiers, List<Item> items) {
		this.board = board;
		this.tiers = tiers;
		this.items = items;
	}

	public Board getBoard() {
		return board;
	}

	public List<Tier> getTiers() {
		return tiers;
	}

	public List<Item> getItems() {
		return items;
	}

}
