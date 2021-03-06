package com.g10.prs;

import com.g10.prs.common.Resources;
import com.g10.prs.common.print.Out;

import java.util.Arrays;

/** This is main class of the cli program. */
public class Program {
    /** The instance of the game. */
    public static PetRescueSaga prs;

    /** Start of the program. */
    public static void main(String[] args) {
        Out.start(Arrays.stream(args).anyMatch(s -> s.equals("-h") || s.equals("--help") || s.equals("-v") || s.equals("--version")));

        prs = new PetRescueSaga();
        int errorCode = prs.run(args);

        if (!inGuiView(args)) {
            Resources.saveSettings();
            Out.end();
            System.exit(errorCode);
        }
    }

    /** @return if we are in gui view or not. */
    private static boolean inGuiView(String[] args) {
        for (String arg : args) {
            if (arg.contains("=gui")) {
                return true;
            }
        }

        return false;
    }
}
