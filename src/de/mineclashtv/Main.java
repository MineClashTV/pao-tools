package de.mineclashtv;

import de.mineclashtv.objects.AnarchyResult;
import de.mineclashtv.tools.Splitter;
import de.mineclashtv.tools.Statistics;

import java.io.File;
import java.util.ArrayList;

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
        File file = new File("image.png");

        ArrayList<AnarchyResult> stats = Statistics.getResults(file);
        for(AnarchyResult result : stats) {
            //System.out.println(result.toString());
            System.out.printf("%dx %s (%s)\n", result.getCount(), result.getColorName(), result.getColorHex());
        }
        Splitter.splitColors(file);
    }

}