package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.UnitCommandTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"unit", "unitCommandType", "target", "x", "y", "extra", "targetPosition",
    "targetTilePosition"})
public class UnitCommand implements Serializable {

  private final IUnit unit;
  private final UnitCommandTypeEnum unitCommandType;
  private final IUnit target;
  private final int x, y;
  private final Integer extra;
  private final IPosition targetPosition;
  private final ITilePosition targetTilePosition;

  public Optional<IUnit> getUnit() {
    return Optional.ofNullable(unit);
  }

  public UnitCommandTypeEnum getEUnitCommandType() {
    return unitCommandType;
  }

  public Optional<IUnit> getTarget() {
    return Optional.ofNullable(target);
  }

  public Optional<Integer> getSlot() {
    if (unitCommandType == UnitCommandTypeEnum.None) {
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
    return (unitCommandType == UnitCommandTypeEnum.Attack_Move
        || unitCommandType == UnitCommandTypeEnum.Attack_Unit
        || unitCommandType == UnitCommandTypeEnum.Move
        || unitCommandType == UnitCommandTypeEnum.Patrol
        || unitCommandType == UnitCommandTypeEnum.Hold_Position
        || unitCommandType == UnitCommandTypeEnum.Stop
        || unitCommandType == UnitCommandTypeEnum.Follow
        || unitCommandType == UnitCommandTypeEnum.Gather
        || unitCommandType == UnitCommandTypeEnum.Return_Cargo
        || unitCommandType == UnitCommandTypeEnum.Repair
        || unitCommandType == UnitCommandTypeEnum.Load
        || unitCommandType == UnitCommandTypeEnum.Unload_All
        || unitCommandType == UnitCommandTypeEnum.Unload_All_Position
        || unitCommandType == UnitCommandTypeEnum.Right_Click_Position
        || unitCommandType == UnitCommandTypeEnum.Right_Click_Unit) && extra != 0;
  }

}