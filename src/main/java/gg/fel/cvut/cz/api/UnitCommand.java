package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.EUnitCommandType;
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
    private final EUnitCommandType unitCommandType;
    private final IUnit target;
    private final int x, y;
    private final Integer extra;
    private final IPosition targetPosition;
    private final ITilePosition targetTilePosition;

    public Optional<IUnit> getUnit() {
        return Optional.ofNullable(unit);
    }

    public EUnitCommandType getEUnitCommandType() {
        return unitCommandType;
    }

    public Optional<IUnit> getTarget() {
        return Optional.ofNullable(target);
    }

    public Optional<Integer> getSlot() {
        if (unitCommandType == EUnitCommandType.None) {
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
        return (unitCommandType == EUnitCommandType.Attack_Move || unitCommandType == EUnitCommandType.Attack_Unit
                || unitCommandType == EUnitCommandType.Move || unitCommandType == EUnitCommandType.Patrol
                || unitCommandType == EUnitCommandType.Hold_Position || unitCommandType == EUnitCommandType.Stop
                || unitCommandType == EUnitCommandType.Follow || unitCommandType == EUnitCommandType.Gather
                || unitCommandType == EUnitCommandType.Return_Cargo || unitCommandType == EUnitCommandType.Repair
                || unitCommandType == EUnitCommandType.Load || unitCommandType == EUnitCommandType.Unload_All
                || unitCommandType == EUnitCommandType.Unload_All_Position || unitCommandType == EUnitCommandType.Right_Click_Position
                || unitCommandType == EUnitCommandType.Right_Click_Unit) && extra != 0;
    }

}