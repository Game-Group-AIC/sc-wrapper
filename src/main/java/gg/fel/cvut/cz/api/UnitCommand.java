package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.UnitCommandType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Optional;

@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"unit", "unitCommandType", "target", "x", "y", "extra", "targetPosition", "targetTilePosition"})
public class UnitCommand implements Serializable {
    private final IUnit unit;
    private final UnitCommandType unitCommandType;
    private final IUnit target;
    private final int x, y;
    private final Integer extra;
    private final IPosition targetPosition;
    private final ITilePosition targetTilePosition;

    public Optional<IUnit> getUnit() {
        return Optional.ofNullable(unit);
    }

    public UnitCommandType getUnitCommandType() {
        return unitCommandType;
    }

    public Optional<IUnit> getTarget() {
        return Optional.ofNullable(target);
    }

    public Optional<Integer> getSlot() {
        if (unitCommandType == UnitCommandType.None) {
            return Optional.ofNullable(extra);
        }
        return Optional.empty();
    }

    public Optional<IPosition> getTargetPosition() {
        return Optional.ofNullable(targetPosition);
    }

    public Optional<ITilePosition> getTargetTilePosition() {
        return Optional.ofNullable(targetTilePosition);
    }

    public boolean isQueued() {
        if (unitCommandType == UnitCommandType.Attack_Move ||
                unitCommandType == UnitCommandType.Attack_Unit ||
                unitCommandType == UnitCommandType.Move ||
                unitCommandType == UnitCommandType.Patrol ||
                unitCommandType == UnitCommandType.Hold_Position ||
                unitCommandType == UnitCommandType.Stop ||
                unitCommandType == UnitCommandType.Follow ||
                unitCommandType == UnitCommandType.Gather ||
                unitCommandType == UnitCommandType.Return_Cargo ||
                unitCommandType == UnitCommandType.Repair ||
                unitCommandType == UnitCommandType.Load ||
                unitCommandType == UnitCommandType.Unload_All ||
                unitCommandType == UnitCommandType.Unload_All_Position ||
                unitCommandType == UnitCommandType.Right_Click_Position ||
                unitCommandType == UnitCommandType.Right_Click_Unit) {
            return extra != 0;
        }
        return false;
    }

}