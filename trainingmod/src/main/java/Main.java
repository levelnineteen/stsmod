import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import training_mod.patches.*;

@SpireInitializer
public class Main implements
        EditCardsSubscriber,   // カードを追加する場合にimplementする
        EditStringsSubscriber  // 言語ファイルを読み込む場合に implementする
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
    }

    @Override
    public void receiveEditCards() {
        // 独自定義したカードを追加
        BaseMod.addCard(new training_mod.cards.TestAttack());
        BaseMod.addCard(new training_mod.cards.TestPower());
        BaseMod.addCard(new training_mod.cards.TestSkill());
    }
}
