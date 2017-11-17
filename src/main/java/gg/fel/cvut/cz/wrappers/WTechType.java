package gg.fel.cvut.cz.wrappers;

import bwapi.TechType;

import java.util.Arrays;
import java.util.List;

import static bwapi.TechType.*;

public class WTechType extends WrapperForClassWithID<TechType> {
    private final static List<TechType> TECH_TYPES = Arrays.asList(Stim_Packs, Lockdown, EMP_Shockwave, Spider_Mines, Scanner_Sweep,
            Tank_Siege_Mode, Defensive_Matrix, Irradiate, Yamato_Gun, Cloaking_Field, Personnel_Cloaking, Restoration,
            Optical_Flare, Healing, Nuclear_Strike, Burrowing, Infestation, Spawn_Broodlings, Dark_Swarm, Plague,
            Consume, Ensnare, Parasite, Lurker_Aspect, Psionic_Storm, Hallucination, Recall, Stasis_Field, Archon_Warp,
            Disruption_Web, Mind_Control, Dark_Archon_Meld, Feedback, Maelstrom, None, Unknown);

    public WTechType(TechType scInstance) {
        super(scInstance, getIndexInList(TECH_TYPES, scInstance));
    }
}
