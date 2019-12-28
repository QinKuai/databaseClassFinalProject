package databaseclass.finalproject.value;

public class ResourceType {
	public static final Integer GAME = 1;
	public static final Integer MOVIE = 2;
	public static final Integer OTHER = 3;
	
	public static final Integer getType(String typeName) {
		switch (typeName) {
		case "game":
			return GAME;
		case "movie":
			return MOVIE;
		case "other":
			return OTHER;
		default:
			return null;
		}
	}
}
