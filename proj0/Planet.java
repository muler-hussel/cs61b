
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double g = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet t){
        return Math.sqrt((t.xxPos - xxPos) * (t.xxPos - xxPos) + (t.yyPos - yyPos) * (t.yyPos - yyPos));
    }
    public double calcForceExertedBy(Planet t){
        return g * t.mass * mass / calcDistance(t) / calcDistance(t);
    }
    public double calcForceExertedByX(Planet t){
        return calcForceExertedBy(t) * (t.xxPos - xxPos) / calcDistance(t);
    }
    public double calcForceExertedByY(Planet t){
        return calcForceExertedBy(t) * (t.yyPos - yyPos) / calcDistance(t);
    }
    public double calcNetForceExertedByX(Planet[] s){
        double ans = 0.0;
        for (Planet pla: s){
            if (this.equals(pla)){
                continue;
            }else {
                ans += calcForceExertedByX(pla);
            }
        }
        return ans;
    }
    public double calcNetForceExertedByY(Planet[] s){
        double ans = 0.0;
        for (Planet pla: s){
            if (this.equals(pla)){
                continue;
            }else {
                ans += calcForceExertedByY(pla);
            }
        }
        return ans;
    }
    public void update(double dt, double fx, double fy){
        double anx = fx / mass;
        double any = fy / mass;
        xxVel += dt * anx;
        yyVel += dt * any;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw(){
        String file = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, file);
    }
}
