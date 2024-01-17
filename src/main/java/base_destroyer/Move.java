package base_destroyer;
import base_destroyer.base.Base;
import base_destroyer.soldier.Soldier;
import base_destroyer.soldier.SoldierView;

public class Move {
    private Base selected;
    private Base target;
    public Move(Base selected, Base target) {
        this.selected = selected;
        this.target = target;
    }
    public void attack() {
        if (target.getAllegiance() != selected.getAllegiance()) {
            target.setGarrison(target.getGarrison() - selected.getGarrison());
            if (target.getGarrison() < 0) {
                target.setAllegiance(selected.getAllegiance());
                target.setGarrison(target.getGarrison() * -1);
            }
            selected.setGarrison(0);
        }
    }
    public void reinforce() {
        if (target.getAllegiance() == selected.getAllegiance() && selected != target) {
            target.setGarrison(target.getGarrison() + selected.getGarrison());
            selected.setGarrison(0);
        }
    }

}
