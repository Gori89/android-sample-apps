package com.ooyala.sample.players;


import android.os.Bundle;

import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.PlayerDomain;
import com.ooyala.android.configuration.Options;
import com.ooyala.android.freewheelsdk.OoyalaFreewheelManager;
import com.ooyala.android.skin.OoyalaSkinLayout;
import com.ooyala.android.skin.OoyalaSkinLayoutController;
import com.ooyala.android.skin.configuration.SkinOptions;
import com.ooyala.sample.R;


/**
 * This activity illustrates how to use Freewheel when all configuration is stored in Ooyala Servers
 *
 * In order for Freewheel to work this simply, you need the following parameters set in your Third Party Module Parameters
 * - fw_android_ad_server
 * - fw_android_player_profile
 *
 * And an Freewheel Ad Spot configured in Backlot with at least the following:
 * - Network ID
 * - Video Asset Network ID
 * - Site Section ID
 *
 */
public class PreconfiguredFreewheelPlayerActivity extends AbstractHookActivity {
	public static String getName() {
		return "Preconfigured Freewheel Player";
	}

	@Override
	void completePlayerSetup(boolean asked) {
		if (asked) {
			// Get the SkinLayout from our layout xml
			OoyalaSkinLayout skinLayout = (OoyalaSkinLayout) findViewById(R.id.ooyalaSkin);

			// Create the OoyalaPlayer, with some built-in UI disabled
			PlayerDomain domain = new PlayerDomain(this.domain);
			Options options = createOptions();
			player = new OoyalaPlayer(pcode, domain, options);

			//Create the SkinOptions, and setup React
			SkinOptions skinOptions = new SkinOptions.Builder().build();
			playerLayoutController = new OoyalaSkinLayoutController(getApplication(), skinLayout, player, skinOptions);
			//Add observer to listen to fullscreen open and close events
			playerLayoutController.addObserver(this);
			player.addObserver(this);

			@SuppressWarnings("unused")
			OoyalaFreewheelManager fwManager = new OoyalaFreewheelManager(this, skinLayout.getAdView(), player);

			if (player.setEmbedCode(embedCode)) {
			}
		}
	}

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_skin_simple_layout);
		completePlayerSetup(asked);
	}
}
