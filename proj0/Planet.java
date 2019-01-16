public class Planet {

    static final double G = 6.67 * 1e-11; // Gravitational constant

    public double xxPos; // current x position
    public double yyPos; // current y position
    public double xxVel; // current velocity in the x direction
    public double yyVel; // current velocity in the y direction
    public double mass; // mass
    public String imgFileName; // name of the file that corresponds to the image that depicts the planet, e.g. jupiter.gif

    /**
        Constructor to initialize an instance of the Planet class with given parameters
        @param xP: current x position
        @param yP: current y position
        @param xV: current velocity in the x direction
        @param yV: current velocity in the y direction
        @param m: mass
        @param img: path to an image file that depicts the planet
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
       Constructor to copy given Planet object
    */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
        /** Below can replaces all above */
        // this.Planet(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /**
        Calculates the distance between two Planets
     */
    public double calcDistance(Planet p) {
        double diffX = xxPos - p.xxPos;
        double diffY = yyPos - p.yyPos;
        double dist = Math.sqrt(diffX * diffX + diffY * diffY);
        return dist;
    }

    /**
        Calculates the force exerted on this Planet by the given Planet
     */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double F =  G * mass * p.mass / (r * r);
        return F;
    }

    /**
        Calculates the force exerted in the X direction
     */
    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffX = p.xxPos - xxPos;
        double fX = F * diffX / r;
        return fX;
    }

    /**
        Calculates the force exerted in the Y direction
     */
    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffY = p.yyPos - yyPos;
        double fY = F * diffY / r;
        return fY;
    }

    /**
        Judges this.Planet is equal to given Plane
     */
    public Boolean equals(Planet p) {
        if (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel &&
            yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName) {
            return true;
        } else {
            return false;
        }
    }

    /**
        Calculates the net X force exerted by all Planets in given array
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double fXNet = 0.0;
        for (Planet p: allPlanets) {
            if (equals(p)) {
                continue;
            } else {
                fXNet += calcForceExertedByX(p);
            }
        }
        return fXNet;
    }

    /**
        Calculates the net Y force exerted by all Planets in given array
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double fYNet = 0.0;
        for (Planet p: allPlanets) {
            if (equals(p)) {
                continue;
            } else {
                fYNet += calcForceExertedByY(p);
            }
        }
        return fYNet;
    }

    /**
        Updates the Planet's velocity and position while given time duration
     */
    public void update(double dt, double fX, double fY) {
        double aXNet = fX / this.mass;
        double aYNet = fY / this.mass;

        this.xxVel += dt * aXNet;
        this.yyVel += dt * aYNet;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    /**
        Draws this.Planet at its position
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}
