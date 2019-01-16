/**
    Tests two Planets' pairwise force between them
 */
public class TestPlanet {

    /**
        Tests calcForceExertedBy
     */
    public static void main(String[] args) {
        checkPairwiseForce();
    }

    /**
        Checks two Planets pull against each other collectly
     */
    private static void checkPairwiseForce() {
        System.out.println("Checking pairwise forces ..");

        Planet sun = new Planet(1.0 * 1e12, 2.0 * 1e11, 0.0, 0.0, 2.0 * 1e30, "images/sun.gif");
        Planet saturn = new Planet(2.3 * 1e12, 9.5 * 1e11, 0.0, 0.0, 6.0 * 1e26, "images/saturn.gif");

        double F = sun.calcForceExertedBy(saturn);

        System.out.println("|F| = " + F + " (should be around 3.6 * 1e22)");
    }

}
