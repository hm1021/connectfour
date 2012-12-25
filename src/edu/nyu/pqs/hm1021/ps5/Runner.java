package edu.nyu.pqs.hm1021.ps5;

import edu.nyu.pqs.hm1021.ps5.controller.Controller;
import edu.nyu.pqs.hm1021.ps5.controller.IController;
import edu.nyu.pqs.hm1021.ps5.view.Observer;

/**
 * This class is just the runner to start the game.
 * @author hiral
 *
 */
public class Runner {

	public static void main(String[] args) {
		IController controller = new Controller();
		new Observer(controller);
		new Observer(controller);
	}
}
