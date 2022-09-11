package me.badgraphixd.expansionproject.skill;

import java.util.List;

public enum ChildSkill implements Skill {

    // only examples

    POTATO_FARMING,
    CARROT_FARMING,
    BEETROOT_FARMING,
    WHEAT_FARMING,
    SUGAR_CANE_FARMING,
    COCOA_FARMING,

    BASIC_CRAFTING,
    WOODWORKING_CRAFTING,
    METALWORKING_CRAFTING,
    CASTING_CRAFTING,
    MASONRY_CRAFTING,

    GENERAL_COMBAT,
    SPEED_COMBAT,
    AWARENESS_COMBAT,
    DODGING_COMBAT,
    SHORT_SWORD_COMBAT,
    LONG_SWORD_COMBAT,
    KNIFE_COMBAT,
    BOW_COMBAT,
    SHIELD_COMBAT,
    AXE_COMBAT,
    SPEAR_COMBAT,

    REGENERATION_SELF_CONTROL,
    COURAGE_SELF_CONTROL,
    EMOTIONAL_SELF_CONTROL,
    AGONY_SELF_CONTROL,

    BASIC_ALCHEMY,
    ADVANCED_ALCHEMY,
    ZOOLOGY_ALCHEMY,
    BOTANY_ALCHEMY,
    MINERAL_ALCHEMY,
    MAGICAL_ALCHEMY,

    BASIC_MAGIC,
    ADVANCED_MAGIC,
    NECROMANCY_MAGIC,
    RITUAL_MAGIC,
    SUMMONING_MAGIC,
    EGO_MAGIC,
    OFFENSIVE_MAGIC,
    DEFENSIVE_MAGIC,

    WEAPON_ENCHANTING,
    ARMOR_ENCHANTING,
    UTIL_ENCHANTING,
    ARTEFACT_ENCHANTING,
    SOULBINDING_ENCHANTING,
    DISENCHANTING,

    STAMINA_AGILITY,
    JUMPING_AGILITY,
    SNEAKING_AGILITY,
    WALKING_AGILITY,
    RUNNING_AGILITY,
    SPRINTING_AGILITY,

    ROCK_MINING,
    WOOD_MINING,
    SIEVE_MINING,
    DIGGING_MINING;

    private ParentSkill parentSkill;

    public void setParentSkill(ParentSkill parentSkill) {
        this.parentSkill = parentSkill;
    }

    public ParentSkill getParentSkill() {
        return parentSkill;
    }

    public List<ChildSkill> getRelatedSkills() {
        return parentSkill.getChildSkills();
    }

}
