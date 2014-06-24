package io.look.attdemoapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		addItem(new DummyItem("1", "1771134", "wireless-mobile-android-english"));
		addItem(new DummyItem("2", "1771134", "service_olam-mobile-web-english"));
		addItem(new DummyItem("3", "10188673", "dslcare9-mobile-android-english"));
		addItem(new DummyItem("4", "10188673", "dslcare13-mobile-android-english"));
		
		//addItem(new DummyItem("1", "P17453170", "mobile"));
		//addItem(new DummyItem("2", "P17453170", "sales"));
		//addItem(new DummyItem("3", "61010431", "mobile"));
		//addItem(new DummyItem("4", "61010431", "otherskill"));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String account;
		public String skill;

		public DummyItem(String id, String account, String skill) {
			this.id = id;
			this.account = account;
			this.skill = skill;
		}

		@Override
		public String toString() {
			return skill;
		}
	}
}
