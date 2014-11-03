import java.util.Arrays;
public class Fast {

    public static void main(String [] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        if(N<4) return;
        Point [] pArray = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pArray[i] = new Point(x, y);
            pArray[i] .draw();
        }
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius(0.001);
        Arrays.sort( pArray );
        //System.out.println(Arrays.deepToString(pArray));
        StdDraw.show(0);
/*        Arrays.sort(pArray
                    //, 0, N - 1
                    , pArray[0].SLOPE_ORDER);*/

        for (int i = 0; i < N; i++) {

            Point refPoint = pArray[i];
                        Arrays.sort(pArray
            	 //, i , N - 1
            	, refPoint.SLOPE_ORDER);
            int j = i + 1;
            Point currPoint = pArray[j];
            j++;
            Point nextPoint = pArray[j];
            double currSlope = refPoint.slopeTo(currPoint);
            double nextSlope = refPoint.slopeTo(nextPoint);
            int count = 0;
            while (j < N-2) {
                if (currSlope == nextSlope) {
                    count++;
                } else {
                    if (count >= 2) {
                        print(i, count, j, pArray);
                    }
                    count = 0;
                }
                j++;
                if (j >= N) {
                    if (count >= 2) {
                        print(i, count, j, pArray);
                        count = 0;

                    }

                }
                if (j < N) {
                    currSlope = nextSlope;
                    nextPoint = pArray[j];
                    nextSlope = refPoint.slopeTo(nextPoint);
                }

            }


        }
        StdDraw.show(0);

    }
    private static void print(int i, int count, int j, Point[] pArray) {
        StdDraw.setPenRadius(0.001);
        System.out.print(i + " " + count + " --- " + pArray[i]);
        j = j - 1;
        // pArray[i].drawTo(pArray[j]);
        while (count >= 0) {
            System.out.print(" -> " + pArray[j] );
            pArray[i].drawTo(pArray[j]);
            j--;
            count--;
        }

        System.out.println("");

        StdDraw.setPenRadius(0);
    }
}