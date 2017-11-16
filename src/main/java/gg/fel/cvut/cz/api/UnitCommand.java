package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.UnitCommandType;

public abstract class UnitCommand {

    private IUnit unit;

    private UnitCommandType unitCommandType;

    private IUnit target;

    private int x, y;

    private int extra;

    private UnitCommand(IUnit unit, UnitCommandType unitCommandType, IUnit target, int x, int y, int extra) {
        this.unit = unit;
        this.unitCommandType = unitCommandType;
        this.target = target;
        this.x = x;
        this.y = y;
        this.extra = extra;
    }

    public IUnit getUnit() {
        return unit;
    }

    public UnitCommandType getUnitCommandType() {
        return unitCommandType;
    }

    public IUnit getTarget() {
        return target;
    }


    public int getSlot() {
        if (unitCommandType == UnitCommandType.None) {
            return extra;
        }
        return -1;
    }

    public abstract IPosition getTargetPosition();

    public abstract ITilePosition getTargetTilePosition();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitCommand)) return false;

        UnitCommand that = (UnitCommand) o;

        if (extra != that.extra) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        if (unitCommandType != null ? !unitCommandType.equals(that.unitCommandType) : that.unitCommandType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + (unitCommandType != null ? unitCommandType.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + extra;
        return result;
    }
}