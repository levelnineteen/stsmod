package training_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class TestRelic extends CustomRelic {
    public static final String ID = "trainingmod:TestRelic";
    public static final String IMG = "img/relics/TestRelic.png";
    public static final String OUTLINE_IMG = "img/relics/outline/TestRelic.png";

    public TestRelic(){
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(OUTLINE_IMG),
                RelicTier.STARTER,  //スターターレリック
                LandingSound.FLAT
        );
    }

    public String getUpdatedDescription(){
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy(){
        return new TestRelic();
    }
}
