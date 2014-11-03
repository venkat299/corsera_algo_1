
import java.util.Arrays;
public class Brute {
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.0001);  // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point [] pArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pArray[i] = new Point(x, y);
            pArray[i] .draw();
        }
        Arrays.sort( pArray );
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius(0.001);

        double slope1, slope2, slope3;
        Point p1, p2, p3, p4;
        for (int i = 0; i < N - 3; i++) {
            p1 = pArray[i];
            for (int j = 1; j < N - 2; j++) {
                if (i <= j ) {
                    p2 = pArray[j]; slope1 = p1.slopeTo(p2);
                    for (int k = 2; k < N - 1; k++) {
                        if (k >= j && k != i) {
                            p3 = pArray[k]; slope2 = p2.slopeTo(p3);
                            if (slope1 == slope2) {
                                for (int l = 3; l < N; l++) {
                                    if (l >= k && l != j && l != i) {
                                        p4 = pArray[l]; slope3 = p3.slopeTo(p4);
                                        StdDraw.setPenRadius();
                                        if (slope1 == slope3 && slope1 == slope2) {
                                            StdDraw.setPenRadius(0.001);
                                            /*System.out.println(i + "" + j + "" + (k) + "" + (l) + " || " + slope1 + " " + slope2 + " " + slope3 + " " + (p1).toString() + " -> " +
                                                               (p2).toString() + " -> " +
                                                               (p3).toString() + " -> "
                                                               + (p4).toString() );*/
                                            System.out.println((p1).toString() + " -> " +
                                                               (p2).toString() + " -> " +
                                                               (p3).toString() + " -> "
                                                               + (p4).toString() );
                                            // p1.drawTo(p2);
                                            // p1.drawTo(p3);
                                            p1.drawTo(p4);
                                            StdDraw.setPenRadius();

                                        } else {
                                            // System.out.println(i + "" + j + "" + (k) + "" + (l) + " || " + slope1 + " " + slope2 + " " + slope3 );
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } StdDraw.show(0);
        StdDraw.setPenRadius();
    }


}
/*
System.out.println(i + "" + j + "" + k + "" + l + " " + p1.toString() + " -> " +
                                               p2.toString() + " -> " +
                                               p3.toString() + " -> "
                                               + p4.toString() +" "+slope1 + " " + slope2 + " " + slope3 );
                            p4.drawTo(p3);
                            p3.drawTo(p2);
                            p2.drawTo(p1);
    System.out.println(i + "" + j + "" + k + "" + l + " " + slope1 + " " + slope2 + " " + slope3 );

*/