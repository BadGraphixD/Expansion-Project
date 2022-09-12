package me.badgraphixd.expansionproject.role;

import me.badgraphixd.expansionproject.skill.ParentSkillModifier;

import java.util.Arrays;
import java.util.List;

public enum Race {

    DRYAD(Species.ELF),
    DARK_ELF(Species.ELF),
    HIGH_ELF(Species.ELF);

    public final Species species;
    public final List<ParentSkillModifier> baseParentSkills;

    Race(Species species, ParentSkillModifier... baseParentSkills) {
        this.species = species;
        this.baseParentSkills = Arrays.asList(baseParentSkills);
    }

}
