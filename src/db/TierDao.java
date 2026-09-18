package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Tier;

public class TierDao {
	public void insert(Tier tier) {
		String sql = "INSERT INTO tiers (board_id,name,sort_order) VALUES (?,?,?)";
		
		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, tier.getBoard().getId());
			preparedStatement.setString(2, tier.getName());
			preparedStatement.setInt(3, tier.getSortOrder());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				tier.setId(generatedKeys.getInt(1));
			} else {
				System.out.println("生成されたIDを取得できませんでした");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Tier findById(int id, Board board) {
		String sql = "SELECT * FROM tiers WHERE id = ? AND board_id = ?";

		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			int boardId = board.getId();

			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, boardId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int tierId = resultSet.getInt("id");
				int sortOrder = resultSet.getInt("sort_order");
				String name = resultSet.getString("name");

				return new Tier(board, tierId, sortOrder, name);
			} else {
				System.out.println("該当のIDのBoardがdbに登録されていません。");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Tier> findByBoard(Board board) {
		String sql = "SELECT * FROM tiers WHERE board_id = ? ORDER BY sort_order";
		List<Tier> tiers = new ArrayList<Tier>();

		try (Connection connection = DatabaseManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			int id = board.getId();

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int tierId = resultSet.getInt("id");
				int sortOrder = resultSet.getInt("sort_order");
				String name = resultSet.getString("name");

				tiers.add(new Tier(board, tierId, sortOrder, name));
			}
			return tiers;
		} catch (SQLException e) {
			e.printStackTrace();
			return tiers;
		}
	}

}
