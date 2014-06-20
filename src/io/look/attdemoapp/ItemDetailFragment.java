package io.look.attdemoapp;

import com.liveperson.mobile.android.LivePerson;
import com.liveperson.mobile.android.service.LPMobileDelegateAPI;
import com.liveperson.mobile.android.service.chat.ChatServiceFactory;
import com.liveperson.mobile.android.service.visit.VisitService;
import com.liveperson.mobile.android.ui.LPMobileDelegateAPIImp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import io.look.attdemoapp.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	private LPMobileDelegateAPIImp delegateAPI;
	
	private Button chatButton;
	
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		
		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail))
					.setText(mItem.skill + " / " + mItem.account);
		}
		
		chatButton = (Button)rootView.findViewById(R.id.chat_button);
		chatButton.setOnClickListener(new OnClickListener() {
		    public void onClick(View button) {
		    	LivePerson.beginChat((ItemDetailActivity)getActivity(), mItem.account, mItem.skill);		    	
		    }			
		});
				
		updateChatButton();
		
		delegateAPI = new LPMobileDelegateAPIImp(){
			@Override
			public void onEnabledChanged(boolean enabled, String account, String skill)
			{
				super.onEnabledChanged(enabled, account, skill);
				
				if (account.equals(mItem.account) && skill.equals(mItem.skill))
				{
					getActivity().runOnUiThread(new Runnable() {
					     @Override
					     public void run() {
								updateChatButton();
					    }
					});
				}
			}
		};
		
        ChatServiceFactory.getInstance().setDelegateAPI(delegateAPI);        
        
		return rootView;
	}
	
	private void updateChatButton(){
		if (LivePerson.getEnabled(mItem.account, mItem.skill))
		{
			LivePerson.setCustomVariable("button-" + mItem.account + "-" + mItem.skill, "1");
			chatButton.setActivated(true);
			chatButton.setAlpha((float) 1.0);
			
			LivePerson.setInvitationShown();
		}
		else
		{
			LivePerson.setCustomVariable("button-" + mItem.account + "-" + mItem.skill, "0");
			chatButton.setActivated(false);
			chatButton.setAlpha((float) 0.3);
			
			LivePerson.setInvitationNotShown();
		}
	}
	
	@Override
	public void onDestroyView () {
		super.onDestroyView();
		LivePerson.setCustomVariable("button-" + mItem.account + "-" + mItem.skill, "-1");
		LivePerson.setInvitationNotAvailable();
        ChatServiceFactory.getInstance().setDelegateAPI(null);
	}	
}
