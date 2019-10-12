
public class MeleeAttack implements Attack {

    Monster attacker;

    public MeleeAttack(Monster attacker){
        this.attacker;
    }

    @Override
    public Integer attack(Monster target){
        String message = attaker + " uses a Melee attack on " + target;
        System.out.println(message);
        return null;
    }
}
