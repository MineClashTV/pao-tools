package de.mineclashtv;

import de.mineclashtv.tools.Splitter;
import de.mineclashtv.tools.Statistics;

import java.io.File;

/**
 * @author Pascal Puffke (https://github.com/MineClashTV)
 */
public class Main {

    public static void main(String[] args) throws Exception {
        /*
        if (args.length == 0) {
            System.err.println("No input file specified!");
            System.exit(1);
        }
        splitColors(new File(args[0]));
        */
        File file = new File("unknown.png");
        Statistics.getImageStats(file);
        Splitter.splitColors(file);
    }

}