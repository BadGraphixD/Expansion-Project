package me.badgraphixd.expansionproject.role;

import me.badgraphixd.expansionproject.skill.ChildSkillModifier;
import me.badgraphixd.expansionproject.skill.ParentSkillModifier;
import me.badgraphixd.expansionproject.skill.SkillModifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

    ROGUE_PRINCE,
    SECOND_SON,
    MERCENARY,
    ELITE_MERCENARY,
    CARPENTER_APPRENTICE,
    HEALER_APPRENTICE,
    ALCHEMY_APPRENTICE,
    FARMER_APPRENTICE,
    SMITH_APPRENTICE,
    MASON_APPRENTICE,
    MINER_APPRENTICE,
    ATHLETE,
    KNIGHT,
    DARK_KNIGHT,
    MAGE_APPRENTICE,
    ADVENTURER;

    public final List<ChildSkillModifier> childSkillModifiers;
    public final List<ParentSkillModifier> parentSkillModifiers;

    Role(SkillModifier... skillModifiers) {
        this.childSkillModifiers = Arrays.stream(skillModifiers)
                .filter(ChildSkillModifier.class::isInstance)
                .map(ChildSkillModifier.class::cast)
                .collect(Collectors.toList());
        this.parentSkillModifiers = Arrays.stream(skillModifiers)
                .filter(ParentSkillModifier.class::isInstance)
                .map(ParentSkillModifier.class::cast)
                .collect(Collectors.toList());
    }

}
