package training_mod.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import training_mod.patches.AbstractCardEnum;

//ブロックに必要

public class CommonSkill0 extends CustomCard {
    public static final String ID = "trainingmod:CommonSkill0";
    // getCardStringsで Mainクラスにて読み込んだ cards-JPN.json 内の文字列情報を取得する
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/card.png";
    private static final int COST = 0;
    private static final int BLOCK_AMT = 4;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public CommonSkill0() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.TRAINING_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF);
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
        this.baseBlock = BLOCK_AMT;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // ブロックを得る
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, this.block)
        );
    }
    @Override
    public boolean isDefend() {
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new CommonSkill0();
    }

    // カードアップグレード時の処理
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
