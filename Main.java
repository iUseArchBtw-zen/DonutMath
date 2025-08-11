public class Main {

    static double clamp (double value,  double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    public static void main(String[] args) throws InterruptedException {

        int width = 120;
        int height = 30;
        double screenRelation = (double) width / height;
        double pixelAspect = 14.0 / 24.0;
        char[] gradient ={' ', '.', ':', '/', 'r', 'O', '@' };
        int gradientSize = gradient.length-1;


        for (int t = 0; ; t++) {
            StringBuilder frame = new StringBuilder();
            frame.append("\033[H");

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double dx = (double)x / width * 2.0 - 1.0;
                    double dy = (double) y / height * 2.0 - 1.0;

                    dx = dx * screenRelation * pixelAspect;
                    dx += Math.sin(t * 0.05);

                    double distanceSquared = dx * dx + dy * dy;
                    double distance = Math.sqrt(distanceSquared);

                    int color = (int) (1.0 / distance);

                    color = (int) clamp(color, 0, gradientSize);

                    frame.append(gradient[color]);
                }
                frame.append('\n');
            }

            System.out.print(frame);
            Thread.sleep(16);
        }
    }
}
