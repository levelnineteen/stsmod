package training_mod.character;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

import training_mod.cards.*;
import training_mod.relics.*;
import training_mod.patches.*;

public class TrainingChar extends CustomPlayer {
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("trainingmod:TrainingChar");
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String TRAININGCHAR_SHOULDER_2 = "img/char/trainingShoulder.png"; // campfire pose
    public static final String TRAININGCHAR_SHOULDER_1 = "img/char/trainingShoulder.png"; // another campfire pose
    public static final String TRAININGCHAR_CORPSE = "img/char/trainingCorpse.png"; // dead corpse

    public TrainingChar(String name){
        super(name,
                TrainingClassEnum.TrainingClass,
                null,
                null,
                null,
                new SpriterAnimation("img/char/anim/trainingChar.scml")
        );
        initializeClass(null,
                TRAININGCHAR_SHOULDER_2,
                TRAININGCHAR_SHOULDER_1,
                TRAININGCHAR_CORPSE,
                getLoadout(),
                20.0F,
                -10.0F,
                220.0F,
                290.0F,
                new EnergyManager(ENERGY_PER_TURN)
        );

    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass){
        return charStrings.NAMES[0];
    }
    @Override
    public String getSpireHeartText(){
        return charStrings.TEXT[0];
    }
    @Override
    public String getLocalizedCharacterName() {
        return charStrings.NAMES[0];
    }
    @Override
    public String getVampireText() {
        //ヴァンパイアイベントのこと。たぶん0がアイアンクラッドで1がサイレントだと思うけど・・・
        return Vampires.DESCRIPTIONS[0];
    }
    public Color getCardRenderColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);   //TRAINING_COLOR_BGと同じがいい？
    }
    public Color getCardTrailColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);   //TRAINING_COLOR_BGと同じがいい？
    }
    public Color getSlashAttackColor() {
        return CardHelper.getColor(100.0f, 50.0f, 50.0f);   //TRAINING_COLOR_BGと同じがいい？
    }
    public AttackEffect[] getSpireHeartSlashEffect() {
        return new AttackEffect[]{
                AttackEffect.SLASH_HEAVY,
                AttackEffect.FIRE,
                AttackEffect.SLASH_DIAGONAL,
                AttackEffect.SLASH_HEAVY,
                AttackEffect.FIRE,
                AttackEffect.SLASH_DIAGONAL
        };
    }
    public AbstractCard getStartCardForEvent() {
        return new TestPower() ;    //何用？
    }
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }
    public void doCharSelectScreenSelectEffect() {
        //キャラ選択したときのサウンドと画面エフェクトだと思う
        CardCrawlGame.sound.playV("AUTOMATON_ORB_SPAWN", 1.75f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }
    public String getCustomModeCharacterButtonSoundKey() {
        return "AUTOMATON_ORB_SPAWN";
    }

    public AbstractCard.CardColor getCardColor() {
        //このキャラで使うカードのカラー
        return AbstractCardEnum.TRAINING_COLOR;
    }

    public AbstractPlayer newInstance() {
        return new TrainingChar(this.name);
    }


    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TestAttack.ID);
        retVal.add(TestAttack.ID);
        retVal.add(TestAttack.ID);
        retVal.add(TestAttack.ID);
        retVal.add(TestAttack.ID);
        retVal.add(TestSkill.ID);
        retVal.add(TestSkill.ID);
        retVal.add(TestSkill.ID);
        retVal.add(TestPower.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TestRelic.ID);
        UnlockTracker.markRelicAsSeen(TestRelic.ID);
        return retVal;
    }

    //キャラ能力を決める部分
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo(
                charStrings.NAMES[0],
                charStrings.TEXT[0],
                STARTING_HP,
                MAX_HP,
                ORB_SLOTS,
                STARTING_GOLD,
                HAND_SIZE,
                this,
                getStartingRelics(),
                getStartingDeck(),
                false);
    }
}
