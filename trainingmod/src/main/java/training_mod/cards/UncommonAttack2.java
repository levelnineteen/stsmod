package training_mod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import training_mod.patches.AbstractCardEnum;

public class UncommonAttack2 extends CustomCard {
    public static final String ID = "trainingmod:UncommonAttack2";
    // getCardStringsで Mainクラスにて読み込んだ cards-JPN.json 内の文字列情報を取得する
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/card.png";
    private static final int COST = 0;
    private static final int ATTACK_DMG = 4;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int VULNERABLE_AMT = 1;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;

    public UncommonAttack2() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.TRAINING_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = VULNERABLE_AMT;
        this.damage=this.baseDamage = ATTACK_DMG;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // ダメージを与える
        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.DamageAction(
                        m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL) // 画面効果
        );
        // 脆弱化をかける
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(                                // バフ/デバフはすべて内部的にはパワー扱い
                        m,
                        p,
                        new VulnerablePower(m, this.magicNumber, false), // 脆弱をm(=敵)にかける
                        this.magicNumber,
                        true,
                        AbstractGameAction.AttackEffect.NONE)            // 画面効果
        );
    }

    @Override
    public AbstractCard makeCopy() {
        return new UncommonAttack2();
    }

    // カードアップグレード時の処理
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
        }
    }
}
