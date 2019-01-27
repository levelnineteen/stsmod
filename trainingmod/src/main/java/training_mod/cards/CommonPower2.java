package training_mod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import training_mod.patches.AbstractCardEnum;
import training_mod.powers.TestPowerGold;


public class CommonPower2 extends CustomCard {
    public static final String ID = "trainingmod:CommonPower2";
    // getCardStringsで Mainクラスにて読み込んだ cards-JPN.json 内の文字列情報を取得する
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/card.png";
    private static final int COST = 0;
    private static final int GETMONEY = 100;
    private static final int UPGRADE_GETMONEY = 10;

    public CommonPower2() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.TRAINING_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = GETMONEY;


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 戦闘終了時にお金をもらう
        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {

            AbstractDungeon.getCurrRoom().addGoldToRewards(this.magicNumber);
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            p,
                            p,
                            new TestPowerGold(p, this.magicNumber),
                            this.magicNumber
                    )
            );
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CommonPower2();
    }

    // カードアップグレード時の処理
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_GETMONEY);
        }
    }
}
