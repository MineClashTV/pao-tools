package de.mineclashtv;

import de.mineclashtv.ui.CLI;

import java.io.IOException;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Main {

	public static final String version = "1.1.0";

	public static void main(String[] args) throws IOException {
		CLI cli = new CLI();
		cli.run();
	}

}