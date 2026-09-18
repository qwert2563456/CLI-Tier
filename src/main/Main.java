package main;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.awt.print.PrinterAbortException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;

import db.DatabaseManager;

import design.*;
import model.*;
import db.*;

public class Main {
	public static void main(String[] args) {


		try (Connection connection = DatabaseManager.getConnection()) {

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		//		List<Tier> tiers = new ArrayList<>();
		//		List<Item> items = new ArrayList<>();
		//
		//		Scanner scanner = new Scanner(System.in);
		//		String keysc = "a";
		//		int loopNum = 0;
		//
		//		System.out.println("ティアを作成してください。");
		//		while (true) {
		//			loopNum++;
		//			System.out.println("上から" + loopNum + "番目 ");
		//			keysc = scanner.nextLine();
		//
		//			if (keysc.equals("q")) {
		//				break;
		//			}
		//
		//			tiers.add(new Tier(board1, loopNum, keysc));
		//		}
		//
		//		tiers.sort(Comparator.comparingInt(Tier::getSortOrder));
		//		loopNum = 0;
		//		keysc = "a";
		//
		//		System.out.println("アイテムを登録してください。");
		//		while (true) {
		//			loopNum++;
		//
		//			System.out.println(loopNum + "個目のアイテム");
		//
		//			System.out.println("名前を登録");
		//			keysc = scanner.nextLine();
		//			String name = keysc;
		//
		//			System.out.println("登録されたtier一覧");
		//			int i = 1;
		//			for (Tier tier : tiers) {
		//				System.out.println(i + tier.getName());
		//				i++;
		//			}
		//
		//			if (keysc.equals("q")) {
		//				break;
		//			}
		//
		//			System.out.println("tierを登録");
		//
		//			keysc = scanner.nextLine();
		//
		//			if (keysc.equals("q")) {
		//				break;
		//			}
		//
		//			int tierNum = Integer.parseInt(keysc);
		//			items.add(new Item(name, tiers.get(tierNum - 1)));
		//
		//		}
		//
		//
		//		for (Tier tier : tiers) {
		//			int position = 1;
		//			for (Item item : items) {
		//				if (item.getTier() == tier) {
		//					item.setPosition(position);
		//					position++;
		//				}
		//			}
		//		}
		//
		//		items.sort(Comparator.comparingInt(Item::getPosition));
		//
		//		System.out.println(board1.getName());
		//
		//		for (Tier tier : tiers) {
		//			System.out.println(tier.getName());
		//			for (Item item : items) {
		//				if (item.getTier() == tier) {
		//					System.out.println(item.getPosition() + ". " + item.getName());
		//				}
		//			}
		//		}

	}
}
