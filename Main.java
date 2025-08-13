public class Main {

    static double clamp (double value,  double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    static Vec3 rotate (Vec3 vec3, double beta, double gamma, double alpha){
        double cosGamma = Math.cos(gamma), sinGamma = Math.sin(gamma);
        double cosBeta = Math.cos(beta), sinBeta = Math.sin(beta);
        double cosAlpha = Math.cos(alpha), sinAlpha = Math.sin(alpha);

        return new Vec3(
                vec3.x*(cosBeta*cosGamma) + vec3.y * (-sinGamma*cosBeta) + vec3.z * sinBeta,
                vec3.x*(sinAlpha*sinBeta*cosGamma + sinGamma * cosAlpha) + vec3.y *
                        (-sinAlpha*sinBeta*sinGamma + cosAlpha*cosGamma) + vec3.z *
                        (-sinAlpha*cosBeta),
                vec3.x * (sinAlpha*sinGamma + (-sinBeta*cosAlpha*cosGamma)) + vec3.y *
                        (sinAlpha*cosGamma + sinBeta*sinGamma*cosAlpha) + vec3.z *
                        cosAlpha*cosBeta);
    }

    public static void main(String[] args) throws InterruptedException {

        int width = 120;
        int height = 30;
        double screenRelation = (double) width / height;
        double pixelAspect = 14.0 / 24.0;
        char[] gradient ={' ','.',',','-','~',':',';','=','!','*','#','W','8','$','@'};
        int gradientSize = gradient.length-1;



        for (int t = 0; ; t++) {
            Vec3 light = new Vec3(-0.5, 0.5, -1.0).norm();
            light = rotate(light, t*0.05, t*0.0, t*0.0);

            Vec3 spherePos = new Vec3(0, 2, 0).norm();

            StringBuilder frame = new StringBuilder();
            frame.append("\033[H");

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Vec2 uv = new Vec2(x, y)
                            .div(new Vec2(width, height))
                            .mul(new Vec2(2.0))
                            .sub(new Vec2(1.0));

                    uv.x = uv.x * screenRelation * pixelAspect;
                    uv.x = uv.x + Math.sin(t * 0.0);

                    Vec3 rayOrigin = new Vec3(-3,0,0);
                    Vec3 rayDirection = new Vec3(2, uv).norm();

                    rayOrigin = rotate(rayOrigin, t*0.0, 0.25, t*0.00);
                    rayDirection = rotate(rayDirection, t*0.0, 0.25, t*0.00);

                    char pixel = ' ';
                    int color = 0;

                    Vec2 intersection = Geometry.sphIntersect(rayOrigin.sub(spherePos),
                                                            rayDirection, 1);

                    if (intersection.x >0) {
                        Vec3 itPoint = rayOrigin.sum(rayDirection.mul(new Vec3(intersection.x)));
                        Vec3 n = itPoint.norm();
                        double diff = n.scalar_product(light);
                        color = (int)(diff * 20);
                    }


                    color = (int) clamp(color, 0, gradientSize);
                    pixel = gradient[color];
                    frame.append(pixel);
                }
                frame.append('\n');
            }

            System.out.print(frame);
            Thread.sleep(16);
        }
    }
}
