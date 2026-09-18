package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import db.BoardData;
import db.ItemDao;
import model.Item;
import model.Tier;

public class ItemService {
	public Item createItem(BoardData boardData, String name) {
		ItemDao itemDao = new ItemDao();

		Item item = new Item(name, boardData.getBoard());
		itemDao.insert(item);
		boardData.getItems().add(item);
		return item;
	}

	public void reNameItem(Item item, String re) {
		ItemDao itemDao = new ItemDao();
		item.setName(re);
		itemDao.update(item);
	}

	public void moveItem(BoardData boardData, Item item, Tier reTier, int rePoti) {
		ItemDao itemDao = new ItemDao();

		if (item.getTier() == reTier) {
			List<Item> tierItems = new ArrayList<Item>();
			for (Item item2 : boardData.getItems()) {
				if (item2.getTier() == item.getTier()) {
					tierItems.add(item2);
				}
			}
			tierItems.sort(Comparator.comparingInt(Item::getPosition));
			tierItems.remove(item);
			tierItems.add(rePoti - 1, item);

			int p = 1;
			for (Item item2 : tierItems) {
				item2.setPosition(p);
				itemDao.update(item2);
				p++;
			}
		} else {
			List<Item> tierItemsMoto = new ArrayList<Item>();
			List<Item> tierItemsSaki = new ArrayList<Item>();

			for (Item item2 : boardData.getItems()) {
				if (item2.getTier() == item.getTier()) {
					tierItemsMoto.add(item2);
				} else if (item2.getTier() == reTier) {
					tierItemsSaki.add(item2);
				}
			}

			tierItemsMoto.sort(Comparator.comparingInt(Item::getPosition));
			tierItemsSaki.sort(Comparator.comparingInt(Item::getPosition));

			tierItemsMoto.remove(item);
			item.setTier(reTier);
			tierItemsSaki.add(rePoti - 1, item);

			int p = 1;
			for (Item item2 : tierItemsMoto) {
				item2.setPosition(p);
				itemDao.update(item2);
				p++;
			}

			p = 1;
			for (Item item2 : tierItemsSaki) {
				item2.setPosition(p);
				itemDao.update(item2);
				p++;
			}
		}
	}

	public void deleteItem(BoardData boardData, Item item) {
		ItemDao itemDao = new ItemDao();
		Tier tier = item.getTier();
	
		itemDao.delete(item);
		boardData.getItems().remove(item);
		
		if (tier != null) {
			List<Item> items = new ArrayList<Item>();
			for (Item item2 : boardData.getItems()) {
				if (item2.getTier() == tier) {
					items.add(item2);
				}
			}
			items.sort(Comparator.comparingInt(Item::getPosition));

			int p = 1;

			for (Item item2 : items) {
				item2.setPosition(p);
				itemDao.update(item2);
				p++;
			}
		}
	}

}
