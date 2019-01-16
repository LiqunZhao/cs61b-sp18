/**
    Actually runs our simulation of an universe specified in one of the data files
 */
public class NBody {

    private static String backgroundImage = "./images/starfield.jpg"; // background image
    private static final String backgroundMusic = "./audio/2001.mid"; // background music

    /**
        Returns a double corresponding to the radius of the universe in given file
     */
    public static double readRadius(String s) {
		In in = new In(s);

        in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    /**
        Returns an array of Planets corresponding to the planets defined in given file
     */
    public static Planet[] readPlanets(String s) {
        In in = new In(s);

        int n = in.readInt();
        in.readDouble();

        Planet[] allPlanets = new Planet[n];
        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, img);
        }

        return allPlanets;
    }

    public static void main(String[] args) {

        /** Read command line argments */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        if (args.length > 3) {
            NBody.backgroundImage = args[3];
        }

        /** Read Planets and the universe radius defined in filename */
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        /** Enables double buffering for smooth animation rendering and prevent it from flickering */
		StdDraw.enableDoubleBuffering();
        /** Scale the universe to r */
        StdDraw.setScale(-radius, radius);
        /** Clear the drawing window. */
        StdDraw.clear();

        /** Play the space-audio */
        StdAudio.play(backgroundMusic);

        /**
            In this main loop, updating and rendering each Planet's movements with increasing time variable: t
         */
        double t = 0.0; // time variable
        int n = allPlanets.length;
        double[] xForces = new double[n];
        double[] yForces = new double[n];
        while (t < T) {

            /** Calculate net forces for each Planet */
            for (int i = 0; i < n; i++) {
                double fX = allPlanets[i].calcNetForceExertedByX(allPlanets);
                double fY = allPlanets[i].calcNetForceExertedByY(allPlanets);
                xForces[i] = fX;
                yForces[i] = fY;
            }

            /** Update each Planet's members */
            for (int i = 0; i < n; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Show the background */
            StdDraw.picture(0, 0, NBody.backgroundImage);

            /** Draw all of the Planets */
            for (Planet p : allPlanets) {
                p.draw();
            }

            /* Shows the drawing to the screen */
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        /**
            Outputs the final states of each Planet for autograder to work correctly
         */
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (Planet p : allPlanets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }

}
