package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.risk.model.FortificationPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.FortificationUI;
import com.risk.ui.ReinforcementsUI;

/**
* The Class RandomBehaviorImpl implements methods of interface {@link com.risk.behavior.PlayerBehavior PlayerBehavior}
* as per the RANDOM player behaviour
*/
public class RandomBehaviorImpl implements PlayerBehavior {

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#reinforce(java.lang.String)
	 */
	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		ReinforcementsUI objUI = new ReinforcementsUI();
		obj.addObserver(objUI);
		obj.reinforceRandom(player);

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#fortify(java.lang.String)
	 */
	@Override
	public void fortify(String player) {

		FortificationPhaseModel obj = new FortificationPhaseModel();
		FortificationUI objUI = new FortificationUI();
		obj.addObserver(objUI);
		obj.randomFortification(player);

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#attack(java.lang.String, java.util.HashMap)
	 */
	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {
		// TODO Auto-generated method stub

	}

}
