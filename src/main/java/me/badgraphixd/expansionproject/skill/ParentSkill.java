package me.badgraphixd.expansionproject.skill;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ParentSkill implements Skill {

    // todo: thoroughly think/design/balance these skills
    // only examples

    FARMING(
            ChildSkill.BASIC_FARMING,
            ChildSkill.ADVANCED_FARMING,
            ChildSkill.EXOTIC_FARMING
    ),
    CRAFTING(
            ChildSkill.WOODWORKING_CRAFTING,
            ChildSkill.METALWORKING_CRAFTING,
            ChildSkill.CASTING_CRAFTING,
            ChildSkill.MASONRY_CRAFTING,
            ChildSkill.COOKING
    ),
    COMBAT(
            ChildSkill.STRENGTH_COMBAT,
            ChildSkill.SPEED_COMBAT,
            ChildSkill.AWARENESS_COMBAT,
            ChildSkill.DODGING_COMBAT,
            ChildSkill.PRECISION_COMBAT,
            ChildSkill.SWORD_COMBAT,
            ChildSkill.KNIFE_COMBAT,
            ChildSkill.BOW_COMBAT,
            ChildSkill.SHIELD_COMBAT,
            ChildSkill.AXE_COMBAT,
            ChildSkill.HORSE_COMBAT
    ),
    SELF_CONTROL(
            ChildSkill.REGENERATION_SELF_CONTROL,
            ChildSkill.COURAGE_SELF_CONTROL,
            ChildSkill.EMOTIONAL_SELF_CONTROL,
            ChildSkill.AGONY_SELF_CONTROL
    ),
    ALCHEMY(
            ChildSkill.BASIC_ALCHEMY,
            ChildSkill.ADVANCED_ALCHEMY,
            ChildSkill.ZOOLOGY_ALCHEMY,
            ChildSkill.BOTANY_ALCHEMY,
            ChildSkill.MINERAL_ALCHEMY,
            ChildSkill.MAGICAL_ALCHEMY,
            ChildSkill.EXOTIC_ALCHEMY
    ),
    MAGIC(
            ChildSkill.BASIC_MAGIC,
            ChildSkill.ADVANCED_MAGIC,
            ChildSkill.NECROMANCY_MAGIC,
            ChildSkill.RITUAL_MAGIC,
            ChildSkill.SUMMONING_MAGIC,
            ChildSkill.EGO_MAGIC,
            ChildSkill.ADVANCED_SPELLBREAKING
    ),
    ENCHANTING(
            ChildSkill.WEAPON_ENCHANTING,
            ChildSkill.ARMOR_ENCHANTING,
            ChildSkill.UTIL_ENCHANTING,
            ChildSkill.ARTEFACT_ENCHANTING,
            ChildSkill.SOULBINDING_ENCHANTING,
            ChildSkill.DISENCHANTING
    ),
    AGILITY(
            ChildSkill.STAMINA_AGILITY,
            ChildSkill.JUMPING_AGILITY,
            ChildSkill.SNEAKING_AGILITY,
            ChildSkill.RUNNING_AGILITY
    ),
    MINING(
            ChildSkill.ROCK_MINING,
            ChildSkill.WOOD_MINING,
            ChildSkill.SIEVE_MINING,
            ChildSkill.DIGGING_MINING,
            ChildSkill.LUCK_MINING
    );

    private final Set<ChildSkill> childSkills;

    ParentSkill(ChildSkill... childSkills) {
        for (ChildSkill childSkill : childSkills) {
            childSkill.setParentSkill(this);
        }
        this.childSkills = new HashSet<>(Arrays.asList(childSkills));
    }

    public Set<ChildSkill> getChildSkills() {
        return childSkills;
    }

}
