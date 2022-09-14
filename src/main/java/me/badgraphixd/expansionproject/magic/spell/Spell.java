package me.badgraphixd.expansionproject.magic.spell;

import me.badgraphixd.expansionproject.magic.Effect;
import me.badgraphixd.expansionproject.magic.EffectInvocation;
import me.badgraphixd.expansionproject.magic.mana.ManaContainer;
import me.badgraphixd.expansionproject.magic.mana.ManaType;
import me.badgraphixd.expansionproject.managers.SpellManager;
import me.badgraphixd.expansionproject.player.PlayerData;
import me.badgraphixd.expansionproject.skill.SkillRequirement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spell {

    protected String name;

    protected ManaType manaType;
    protected int manaCost;

    protected List<SkillRequirement> skillRequirements = new ArrayList<>();
    protected List<Effect> effects = new ArrayList<>();

    public Spell(String name, ManaType manaType, int manaCost) {
        this.name = name;
        this.manaType = manaType;
        this.manaCost = manaCost;
        SpellManager.register(this);
    }

    public Spell addRequirements(SkillRequirement... skillRequirements) {
        this.skillRequirements.addAll(Arrays.asList(skillRequirements));
        return this;
    }

    public Spell addEffects(Effect... effects) {
        this.effects.addAll(Arrays.asList(effects));
        return this;
    }

    public boolean canCast(ManaContainer manaContainer) {
        return manaContainer.get(manaType) >= manaCost;
    }

    public void cast(EffectInvocation invocation, float relativeSkillLevel) {
        for (Effect effect : effects) {
            if (effect.tryInvoke(relativeSkillLevel))
                effect.invoke(invocation);
        }
    }

    public void cast(EffectInvocation invocation, ManaContainer manaContainer, float relativeSkillLevel) {
        if (canCast(manaContainer)) {
            manaContainer.subtract(manaType, manaCost);
            cast(invocation, relativeSkillLevel);
        }
    }

    public void cast(EffectInvocation invocation, PlayerData data) {
        float relativeSkillLevel = 1f;
        for (SkillRequirement skillRequirement : skillRequirements) {

            int skillLevel = data.getSkillSet().getLevel(skillRequirement.skill);
            relativeSkillLevel = Math.min(relativeSkillLevel,
                    (float)(skillLevel - skillRequirement.minLevel) /
                    (float)(skillRequirement.maxLevel - skillRequirement.minLevel)
            );

            if (relativeSkillLevel <= 0)
                return;
        }

        cast(invocation, data.getManaContainer(), relativeSkillLevel);
    }

    public String getName() {
        return name;
    }

}
