public class Main {

    static double clamp (double value,  double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public static void main(String[] args) throws InterruptedException {

        int width = 120;
        int height = 30;
        double screenRelation = (double) width / height;
        double pixelAspect = 14.0 / 24.0;
        char[] gradient ={' ','.'};
        int gradientSize = gradient.length-1;


        for (int t = 0; ; t++) {
            Vec3 light = new Vec3(Math.cos(t * 0.05), Math.sin(t * 0.05), -1.0).norm();
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

                    Vec3 rayOrigin = new Vec3(-2,0,0);
                    Vec3 rayDirection = new Vec3(1, uv).norm();



                    char pixel = ' ';
                    int color = 0;

                    Vec2 intersection = Geometry.sphIntersect(rayOrigin,
                                                            rayDirection,
                                                            1);
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
