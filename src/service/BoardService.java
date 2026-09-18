package service;

import java.util.ArrayList;
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

	public BoardData createBoard(String name) {
		Board board = new Board(name);
		BoardDao boardDao = new BoardDao();
		boardDao.insert(board);
		List<Tier> tiers = new ArrayList<Tier>();
		List<Item> items = new ArrayList<Item>();

		return new BoardData(board, tiers, items);
	}

	public void reNameBoard(BoardData boardData, String re) {
		BoardDao boardDao = new BoardDao();
		boardData.getBoard().setName(re);
		boardDao.update(boardData.getBoard());
	}

	public void deleteBoard(BoardData boardData) {
		BoardDao boardDao = new BoardDao();
		boardDao.delete(boardData.getBoard());
	}

	public List<Board> getBoards() {
		BoardDao boardDao = new BoardDao();
		return boardDao.findAll();
	}
}
