package com.g10.prs.view.cli;

import com.g10.prs.PetRescueSaga;
import com.g10.prs.common.Resources;
import com.g10.prs.level.Level;

import java.util.*;

/** Menu to choose the level */
public class ChooseLevelMenu extends CliMenu {
    /** list of all the levels */
    List<String> levels;

    /** class constructor */
    public ChooseLevelMenu() {
        super("Choisir un niveau");

        levels = new ArrayList<>();
        levels.addAll(Resources.getCampaignLevelsList());

        String[] levelNames = new String[levels.size()];

        for (int i = 0; i < levelNames.length; i++) {
            try {
                levelNames[i] = Level.load(Resources.getLevelsDirectory() + "/" + levels.get(i)).getName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.categories = levelNames;
    }

    /** handle the answer of the player */
    @Override
    public void handleChoice(int choice) {
        try {
            PetRescueSaga.level = Level.load(Resources.getLevelsDirectory() + "/" + levels.get(choice - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PetRescueSaga.view.changeMenu(new PlayLevelMenu());
    }
}
