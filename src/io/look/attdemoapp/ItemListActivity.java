package io.look.attdemoapp;

import io.look.attdemoapp.dummy.DummyContent.DummyItem;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.liveperson.mobile.android.LivePerson;
import com.liveperson.mobile.android.service.ServicesSharedPreferences;
import com.liveperson.mobile.android.service.chat.ChatServiceFactory;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
		ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		ServicesSharedPreferences.savePackageName(getApplicationContext(), "com.liveperson.mobile.ecosmart-88310755");
		//ServicesSharedPreferences.savePackageName(getApplicationContext(), "com.liveperson.mobile.ecosmart-P17453170");
        ServicesSharedPreferences.saveIsProductionEnv(getApplicationContext(), false);
        
        LivePerson.init(this);        
		
		setContentView(R.layout.activity_item_list);

		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		
		LivePerson.setCustomVariable("button-1771134-wireless-mobile-android-english", "-1");
		LivePerson.setCustomVariable("button-1771134-service_olam-mobile-web-english", "-1");
		LivePerson.setCustomVariable("button-61010431-dslcare9-mobile-android-english", "-1");		
		LivePerson.setCustomVariable("button-61010431-dslcare13-mobile-android-english", "-1");
		
		//LivePerson.setCustomVariable("button-P17453170-mobile", "-1");
		//LivePerson.setCustomVariable("button-P17453170-sales", "-1");
		//LivePerson.setCustomVariable("button-61010431-mobile", "-1");		
		//LivePerson.setCustomVariable("button-61010431-otherskill", "-1");
	}
	
	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
