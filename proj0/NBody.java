public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] allplanet = new Planet[num];
        int i = 0;
        while (i < num){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allplanet[i] = new Planet(xP, yP, vX, vY, m, img);
            i ++;
        }
        return allplanet;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] ap = readPlanets(filename);
        double r = readRadius(filename);

        StdDraw.setScale(-r, r);

        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        double t = 0.0;
        while (t < T){
            Double[] xForces = new Double[ap.length];
            Double[] yForces = new Double[ap.length];
            for (int i = 0; i < ap.length; i ++){
                xForces[i] = ap[i].calcNetForceExertedByX(ap);
                yForces[i] = ap[i].calcNetForceExertedByY(ap);
            }
            for (int i = 0; i < ap.length; i ++){
                ap[i].update(t, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet s : ap){
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", ap.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < ap.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ap[i].xxPos, ap[i].yyPos, ap[i].xxVel,
                    ap[i].yyVel, ap[i].mass, ap[i].imgFileName);
        }
    }
}
