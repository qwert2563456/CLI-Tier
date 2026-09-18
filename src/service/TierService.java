package service;

import db.BoardData;
import db.ItemDao;
import db.TierDao;
import model.Item;
import model.Tier;

public class TierService {
	public Tier createTier(BoardData boardData, String name, int sortOrder) {
		TierDao tierDao = new TierDao();

		Tier tier = new Tier(boardData.getBoard(), sortOrder, name);
		tierDao.insert(tier);
		boardData.getTiers().add(tier);
		return tier;
	}

	public void reName(Tier tier, String re) {
		TierDao tierDao = new TierDao();
		tier.setName(re);
		tierDao.update(tier);
	}

	public void moveTier(Tier tier, BoardData boardData, int moveNum) {
		TierDao tierDao = new TierDao();
		boardData.getTiers().remove(tier);
		boardData.getTiers().add(moveNum - 1, tier);

		int i = 1;
		for (Tier tier2 : boardData.getTiers()) {
			tier2.setSortOrder(i);
			tierDao.update(tier2);
			i++;
		}
	}

	public void deleteTier(BoardData boardData, Tier tier) {
		TierDao tierDao = new TierDao();
		ItemDao itemDao = new ItemDao();

		for (Item item : boardData.getItems()) {
			if (item.getTier() == tier) {
				item.setTier(null);
				itemDao.update(item);
			}
		}

		tierDao.delete(tier);
		boardData.getTiers().remove(tier);

		int i = 1;
		for (Tier tier2 : boardData.getTiers()) {
			tier2.setSortOrder(i);
			tierDao.update(tier2);
			i++;
		}
	}
}
