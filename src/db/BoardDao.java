package db;

import model.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BoardDao {
	public void insert(Board board) {
		String sql = "INSERT INTO boards (name) VALUES (?)";
		
		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, board.getName());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				board.setId(generatedKeys.getInt(1));
			} else {
				System.out.println("生成されたIDを取得できませんでした");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Board findById(int id) {
		String sql = "SELECT * FROM boards WHERE id = (?)";
		
		try (Connection connection = DatabaseManager.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int boardId = resultSet.getInt("id");
				String boardName = resultSet.getString("name");
				return new Board(boardId, boardName);
			} else {
				System.out.println("該当のIDのBoardがdbに登録されていません。");
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Board> findAll() {
		List<Board> boards = new ArrayList<Board>();
		String sql = "SELECT * FROM boards";

		try (Connection connection = DatabaseManager.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				boards.add(new Board(id, name));
			}
			return boards;
		} catch (SQLException e) {
			e.printStackTrace();
			return boards;
		}
	}

	public void update(Board board) {
		String sql = "UPDATE boards SET name = ? WHERE id = ?";

		try (Connection connection = DatabaseManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, board.getName());
			preparedStatement.setInt(2, board.getId());

			int upInt = preparedStatement.executeUpdate();

			if (upInt == 0) {
				throw new IllegalArgumentException("該当するボードがありません。id: " + board.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Board board) {
		String sql = "DELETE FROM boards WHERE id = ?";

		try (Connection connection = DatabaseManager.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, board.getId());

			int delInt = preparedStatement.executeUpdate();

			if (delInt == 0) {
				throw new IllegalArgumentException("該当するボードがありませんでした。");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
