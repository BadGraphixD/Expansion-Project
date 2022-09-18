package me.badgraphixd.expansionproject.skill;

import java.util.Set;

public enum ChildSkill implements Skill {

    BASIC_FARMING, // wheat, carrot, potato, beetroot, pumpkin, trees
    ADVANCED_FARMING, // melon, kelp, bamboo, sugar cane, cactus, mushroom
    EXOTIC_FARMING, // nether wart

    WOODWORKING_CRAFTING, // working with wood
    METALWORKING_CRAFTING, // forming metals
    CASTING_CRAFTING, // creating alloys
    MASONRY_CRAFTING, // working with stone
    COOKING, // working with eatables

    STRENGTH_COMBAT, // attack damage
    SPEED_COMBAT, // attack speed
    AWARENESS_COMBAT, // less damage from behind/less critical damage received
    DODGING_COMBAT, // bigger chance to dodge
    PRECISION_COMBAT, // bigger chance to land hits/deal critical hits

    SWORD_COMBAT, // higher combat stats with sword
    KNIFE_COMBAT, // higher combat stats with knife
    BOW_COMBAT, // higher combat stats with bow
    SHIELD_COMBAT, // higher combat stats with shield
    AXE_COMBAT, // higher combat stats with axe
    HORSE_COMBAT, // higher combat stats on horseback (combat stats on horeseback are by default much lower)

    REGENERATION_SELF_CONTROL, // regenerate faster
    COURAGE_SELF_CONTROL, // less affected by fear and intimidation
    EMOTIONAL_SELF_CONTROL, // more control during dialogue, adrenaline has less negative impacts
    AGONY_SELF_CONTROL, // less negative impacts from injury/painful effects

    BASIC_ALCHEMY, // brewing weak potion levels
    ADVANCED_ALCHEMY, // brewing high potion levels
    ZOOLOGY_ALCHEMY, // brewing/working with animal/monster parts
    BOTANY_ALCHEMY, // brewing/working with plants
    MINERAL_ALCHEMY, // brewing/working with minerals
    MAGICAL_ALCHEMY, // brewing/working with magical ingredients
    EXOTIC_ALCHEMY, // brewing/working with exotic ingredients

    BASIC_MAGIC, // casting simple spells, using weak magical items
    ADVANCED_MAGIC, // casting advanced spells, using powerful magical items
    NECROMANCY_MAGIC, // extracting skills/information from corpses
    RITUAL_MAGIC, // performing rituals
    SUMMONING_MAGIC, // summoning entities
    EGO_MAGIC, // modifying and enhancing the own avatar
    ADVANCED_SPELLBREAKING, // removing powerful effects/spells

    WEAPON_ENCHANTING, // enchanting weapons
    ARMOR_ENCHANTING, // enchanting armor
    UTIL_ENCHANTING, // enchanting tools/utils
    ARTEFACT_ENCHANTING, // enchanting special items
    SOULBINDING_ENCHANTING, // soulbinding in general (more difficult the more powerful the item is)
    DISENCHANTING, // removing enchantments

    STAMINA_AGILITY, // higher stamina
    JUMPING_AGILITY, // less stamina consumption from jumping
    SNEAKING_AGILITY, // sneaking faster/more quiet
    RUNNING_AGILITY, // running faster/more quiet/less stamina consumption

    ROCK_MINING, // mining faster/less stamina consumption
    WOOD_MINING, // breaking faster/less stamina consumption
    SIEVE_MINING, // sieving faster/less stamina consumption
    DIGGING_MINING, // shoveling faster/less stamina consumption
    LUCK_MINING; // more mining/sieving fortune (smaller chance to break gems)

    private ParentSkill parentSkill;

    public void setParentSkill(ParentSkill parentSkill) {
        this.parentSkill = parentSkill;
    }

    public ParentSkill getParentSkill() {
        return parentSkill;
    }

    public Set<ChildSkill> getRelatedSkills() {
        return parentSkill.getChildSkills();
    }

}
