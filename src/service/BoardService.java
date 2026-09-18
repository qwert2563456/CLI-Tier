package service;

import java.util.List;

import db.BoardDao;
import db.BoardData;
import db.ItemDao;
import db.TierDao;
import model.Board;
import model.Item;
import model.Tier;

public class BoardService {
	public BoardData loadBoard(int boardId) {
		Board board = new BoardDao().findById(boardId);
		if (board == null) {
			return null;
		}

		List<Tier> tiers = new TierDao().findByBoard(board);
		List<Item> items = new ItemDao().findByBoard(board, tiers);

		return new BoardData(board, tiers, items);
	}
}
