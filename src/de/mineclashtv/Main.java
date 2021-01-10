package de.mineclashtv;

import de.mineclashtv.ui.CLI;

import java.io.IOException;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Main {

    /*
    TODO clean up literally every single line of this project. this is one giant mess
     */

	public static void main(String[] args) throws IOException {
		CLI cli = new CLI();
		cli.start();
	}

}