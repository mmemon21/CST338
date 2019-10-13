public class RangedAttack implements Attack {

    Monster attacker;

    public RangedAttack(Monster attacker){
        this.attacker;
    }

    @Override
    public Integer attack(Monster target){
        String message = attaker + " uses a Ranged attack on " + target;
        System.out.println(message);
        return null;
    }
}
