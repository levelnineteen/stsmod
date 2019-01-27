import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import training_mod.patches.*;
import training_mod.relics.*;

@SpireInitializer
public class Main implements
        PostInitializeSubscriber,   //バッジとか表示するやつ？
        EditCardsSubscriber,   // カードを追加する場合にimplementする
        EditRelicsSubscriber,   //レリックを追加する場合にimplement
        EditStringsSubscriber,  // 言語ファイルを読み込む場合に implementする
        EditCharactersSubscriber //キャラを追加する場合にimplementする
{
    private static final Color TRAINING_COLOR_BG = CardHelper.getColor(100.0f, 50.0f, 50.0f);
    private static final String ATTACK_TRAINING         = "img/cards/bg_attack_512.png";
    private static final String SKILL_TRAINING          = "img/cards/bg_skill_512.png";
    private static final String POWER_TRAINING           = "img/cards/bg_power_512.png";
    private static final String ENERGY_ORB_TRAINING         = "img/cards/orb_512.png";
    private static final String ATTACK_PORT_TRAINING        = "img/cards/bg_attack_1024.png";
    private static final String SKILL_PORT_TRAINING         = "img/cards/bg_skill_1024.png";
    private static final String POWER_PORT_TRAINING         = "img/cards/bg_power_1024.png";
    private static final String ENERGY_ORB_PORT_TRAINING = "img/cards/orb_1024.png";
    private static final String ENERGY_ORB_CARD_TRAINING = "img/cards/orb_ui.png";

    private static final String MODNAME = "TrainingMod";
    private static final String AUTHOR = "levelninteen";
    private static final String DESCRIPTION = "TEST.";

    public static final Logger logger = LogManager.getLogger(Main.class.getName());


    public Main(){
        BaseMod.subscribe(this);
        BaseMod.addColor(
                AbstractCardEnum.TRAINING_COLOR    //color
                , TRAINING_COLOR_BG //bgColor
                , TRAINING_COLOR_BG//backColor
                , TRAINING_COLOR_BG//frameColor
                , TRAINING_COLOR_BG//frameOutlineColor
                , TRAINING_COLOR_BG//descBoxColor
                , TRAINING_COLOR_BG //trailVfColor
                , TRAINING_COLOR_BG//glowColor
                , ATTACK_TRAINING//attackBg
                , SKILL_TRAINING//skillBg
                , POWER_TRAINING//powerBG
                , ENERGY_ORB_TRAINING//energyOrb
                , ATTACK_PORT_TRAINING//attackBgPortrait
                , SKILL_PORT_TRAINING//skillBgPortrait
                , POWER_PORT_TRAINING//powerBgPortrait
                , ENERGY_ORB_PORT_TRAINING//energyOrbPortrait
                , ENERGY_ORB_CARD_TRAINING//CardEnergyOrb
        );
    }

    // @SpireInitializerで修飾したクラスはこのメソッドを定義する必要がある
    public static void initialize() {
        Main main = new Main();
    }

    @Override
    public void receiveEditStrings() {
        // 独自定義した言語ファイルを追加
        // Settings.languageには日本語ならJPN, 英語ならENGが入っている
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/cards-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/relics-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "localization/char-" + Settings.language + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/powers-" + Settings.language + ".json");
    }

    @Override
    public void receiveEditCards() {
        // 独自定義したカードを追加
        BaseMod.addCard(new training_mod.cards.TestAttack());
        BaseMod.addCard(new training_mod.cards.TestPower());
        BaseMod.addCard(new training_mod.cards.TestSkill());
        BaseMod.addCard(new training_mod.cards.CommonAttack0());
        BaseMod.addCard(new training_mod.cards.CommonAttack1());
        BaseMod.addCard(new training_mod.cards.CommonAttack2());
        BaseMod.addCard(new training_mod.cards.CommonAttack3());
        BaseMod.addCard(new training_mod.cards.UncommonAttack0());
        BaseMod.addCard(new training_mod.cards.UncommonAttack1());
        BaseMod.addCard(new training_mod.cards.UncommonAttack2());
        BaseMod.addCard(new training_mod.cards.UncommonAttack3());
        BaseMod.addCard(new training_mod.cards.RareAttack0());
        BaseMod.addCard(new training_mod.cards.RareAttack1());
        BaseMod.addCard(new training_mod.cards.RareAttack2());
        BaseMod.addCard(new training_mod.cards.RareAttack3());
        BaseMod.addCard(new training_mod.cards.CommonSkill0());
        BaseMod.addCard(new training_mod.cards.CommonSkill1());
        BaseMod.addCard(new training_mod.cards.CommonSkill2());
        BaseMod.addCard(new training_mod.cards.CommonSkill3());
        BaseMod.addCard(new training_mod.cards.UncommonSkill0());
        BaseMod.addCard(new training_mod.cards.UncommonSkill1());
        BaseMod.addCard(new training_mod.cards.UncommonSkill2());
        BaseMod.addCard(new training_mod.cards.UncommonSkill3());
        BaseMod.addCard(new training_mod.cards.RareSkill0());
        BaseMod.addCard(new training_mod.cards.RareSkill1());
        BaseMod.addCard(new training_mod.cards.RareSkill2());
        BaseMod.addCard(new training_mod.cards.RareSkill3());
        BaseMod.addCard(new training_mod.cards.CommonPower0());
        BaseMod.addCard(new training_mod.cards.CommonPower1());
        BaseMod.addCard(new training_mod.cards.CommonPower2());
        BaseMod.addCard(new training_mod.cards.CommonPower3());
        BaseMod.addCard(new training_mod.cards.UncommonPower0());
        BaseMod.addCard(new training_mod.cards.UncommonPower1());
        BaseMod.addCard(new training_mod.cards.UncommonPower2());
        BaseMod.addCard(new training_mod.cards.UncommonPower3());
        BaseMod.addCard(new training_mod.cards.RarePower0());
        BaseMod.addCard(new training_mod.cards.RarePower1());
        BaseMod.addCard(new training_mod.cards.RarePower2());
        BaseMod.addCard(new training_mod.cards.RarePower3());
    }

    @Override
    public void receiveEditCharacters() {
        //独自定義のキャラの追加
        BaseMod.addCharacter(
                new training_mod.character.TrainingChar("TrainingCharacter"),
                "img/charSelect/trainingButton.png",
                "img/charSelect/trainingPortrait.png",
                TrainingClassEnum.TrainingClass
        );

    }

    public void receiveEditRelics() {
        //レリックの追加
        logger.info("begin editing relics");
        BaseMod.addRelicToCustomPool(
                new TestRelic(),
                AbstractCardEnum.TRAINING_COLOR //カラーに対して追加する。共通レリックはまた別のメソッド
        );
        logger.info("done editing relics");
    }

    public void receivePostInitialize(){
        Texture badgeTexture = new Texture("img/TrainingBadge.png");
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, null);
    }
}
