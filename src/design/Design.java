package design;

import db.BoardData;

public class Design {

	public static void title() {

		System.out.println("""
				████████╗██╗███████╗██████╗     ██╗     ██╗███████╗████████╗
				╚══██╔══╝██║██╔════╝██╔══██╗    ██║     ██║██╔════╝╚══██╔══╝
				   ██║   ██║█████╗  ██████╔╝    ██║     ██║███████╗   ██║
				   ██║   ██║██╔══╝  ██╔══██╗    ██║     ██║╚════██║   ██║
				   ██║   ██║███████╗██║  ██║    ███████╗██║███████║   ██║
				   ╚═╝   ╚═╝╚══════╝╚═╝  ╚═╝    ╚══════╝╚═╝╚══════╝   ╚═╝
				""");

	}

	public static void showBoard(BoardData boardData) {
		System.out.println(boardData.getBoard().getName());

		for (int i = 0; i < boardData.getTiers().size(); i++) {
			System.out.println(boardData.getTiers().get(i).getName());
			for(int j = 0; j < boardData.getItems().size(); j++) {
					System.out.println("  " + boardData.getItems().get(j).getName());
			}
		}
	}
}
