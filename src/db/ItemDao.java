package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Item;
import model.Tier;

public class ItemDao {
	public void insert(Item item) {
		String sql = "INSERT INTO items (board_id,name) VALUES (?,?)";

		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, item.getBoard().getId());
			preparedStatement.setString(2, item.getName());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				item.setId(generatedKeys.getInt(1));
			} else {
				System.out.println("生成されたIDを取得できませんでした");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Item findById(int id, Board board, List<Tier> tiers) {

		String sql = "SELECT * FROM items WHERE id = ? AND board_id = ?";

		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, board.getId());

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int itemId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int position = resultSet.getInt("position");
				String comment = resultSet.getString("comment");
				Integer tierId = resultSet.getObject("tier_id", Integer.class);
				Tier itemTier = null;

				if (tierId != null) {
					for (Tier tier : tiers) {
						if (tier.getId() == tierId) {
							itemTier = tier;
							break;
						}
					}

					if (itemTier == null) {
						System.out.println("Itemに設定されているTierがTier一覧に存在しません。");
						return null;
					}
				}

				Item item = new Item(name, board);

				item.setId(itemId);
				item.setTier(itemTier);
				item.setPosition(position);
				item.setComment(comment);
				return item;

			} else {
				System.out.println("指定したBoardに該当するItemが登録されていません。");
				return null;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	public List<Item> findByBoard(Board board, List<Tier> tiers){

		String sql = "SELECT * FROM items WHERE board_id = ?";
		List<Item> items = new ArrayList<Item>();
		
		try(Connection connection = DatabaseManager.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, board.getId());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int itemId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int position = resultSet.getInt("position");
				String comment = resultSet.getString("comment");
				Integer tierId = resultSet.getObject("tier_id", Integer.class);
				Tier itemTier = null;
				
				if (tierId != null) {
					for (Tier tier : tiers) {
						if (tier.getId() == tierId) {
							itemTier = tier;
							break;
						}
					}
				}

				Item item = new Item(itemId, itemTier, board, name, position, comment);
				items.add(item);
			}

			return items;

		} catch (SQLException e) {
			e.printStackTrace();
			return items;
		}
	}

	public void update(Item item) {
		String sql = "UPDATE items SET name = ?, tier_id = ?, position = ?, comment = ? WHERE id = ? AND board_id = ?";
		try (Connection connection = DatabaseManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, item.getName());
			if (item.getTier() == null) {
				preparedStatement.setObject(2, null);
			} else {
				preparedStatement.setInt(2, item.getTier().getId());
			}
			preparedStatement.setInt(3, item.getPosition());
			preparedStatement.setString(4, item.getComment());
			preparedStatement.setInt(5, item.getId());
			preparedStatement.setInt(6, item.getBoard().getId());

			int upInt = preparedStatement.executeUpdate();

			if (upInt == 0) {
				throw new IllegalArgumentException("該当するitemがありません");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Item item) {
		String sql = "DELETE FROM items WHERE id = ? AND board_id = ?";
		try (Connection connection = DatabaseManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, item.getId());
			preparedStatement.setInt(2, item.getBoard().getId());

			int delInt = preparedStatement.executeUpdate();

			if (delInt == 0) {
				throw new IllegalArgumentException("該当するitemが見つかりませんでした。");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
