package me.badgraphixd.expansionproject.skill;

import java.util.Arrays;
import java.util.List;

public enum ParentSkill implements Skill {

    // todo: thoroughly think/design/balance these skills
    // only examples

    FARMING(
            ChildSkill.POTATO_FARMING,
            ChildSkill.CARROT_FARMING,
            ChildSkill.BEETROOT_FARMING,
            ChildSkill.WHEAT_FARMING,
            ChildSkill.SUGAR_CANE_FARMING,
            ChildSkill.COCOA_FARMING
    ),
    CRAFTING(
            ChildSkill.BASIC_CRAFTING,
            ChildSkill.WOODWORKING_CRAFTING,
            ChildSkill.METALWORKING_CRAFTING,
            ChildSkill.CASTING_CRAFTING,
            ChildSkill.MASONRY_CRAFTING
    ),
    COMBAT(
            ChildSkill.GENERAL_COMBAT,
            ChildSkill.SPEED_COMBAT,
            ChildSkill.AWARENESS_COMBAT,
            ChildSkill.DODGING_COMBAT,
            ChildSkill.SHORT_SWORD_COMBAT,
            ChildSkill.LONG_SWORD_COMBAT,
            ChildSkill.KNIFE_COMBAT,
            ChildSkill.BOW_COMBAT,
            ChildSkill.SHIELD_COMBAT,
            ChildSkill.AXE_COMBAT,
            ChildSkill.SPEAR_COMBAT
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
            ChildSkill.MAGICAL_ALCHEMY
    ),
    MAGIC(
            ChildSkill.BASIC_MAGIC,
            ChildSkill.ADVANCED_MAGIC,
            ChildSkill.NECROMANCY_MAGIC,
            ChildSkill.RITUAL_MAGIC,
            ChildSkill.SUMMONING_MAGIC,
            ChildSkill.EGO_MAGIC,
            ChildSkill.OFFENSIVE_MAGIC,
            ChildSkill.DEFENSIVE_MAGIC
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
            ChildSkill.WALKING_AGILITY,
            ChildSkill.RUNNING_AGILITY,
            ChildSkill.SPRINTING_AGILITY
    ),
    MINING(
            ChildSkill.ROCK_MINING,
            ChildSkill.WOOD_MINING,
            ChildSkill.SIEVE_MINING,
            ChildSkill.DIGGING_MINING
    );

    private final List<ChildSkill> childSkills;

    ParentSkill(ChildSkill... childSkills) {
        for (ChildSkill childSkill : childSkills) {
            childSkill.setParentSkill(this);
        }
        this.childSkills = Arrays.asList(childSkills);
    }

    public List<ChildSkill> getChildSkills() {
        return childSkills;
    }

}
