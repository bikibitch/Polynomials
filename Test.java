import java.io.FileNotFoundException;


public class Test {

    public static void main(String[] args) {
        Grid grid = new Grid();
        try {
            grid.init("file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        Lagrange l = new Lagrange(grid);
        Newton n = new Newton(grid);
        System.out.println();
        System.out.println("Lagrange:");
        l.polynomial.print();
        System.out.println("Newton:");
        n.polynomial.print();
        System.out.println();
        System.out.printf("%15s%15s%15s%15s%15s","X", "Y(X)", "F(X)", "Lagrange", "Newton");
        System.out.println();

        for (int i = 0; i < grid.array[0].length; i++) {
            System.out.printf("%15.6E", grid.array[0][i]);
            System.out.printf("%15.6E", grid.array[1][i]);
            System.out.printf("%15.6E", grid.array[1][i]);
            System.out.printf("%15.6E", l.polynomial.findPolyByHorner(grid.array[0][i]));
            System.out.printf("%15.6E", n.polynomial.findPolyByHorner(grid.array[0][i]));
            System.out.println();
            if (i != grid.array[0].length - 1) {
                double x = grid.array[0][i] + grid.step / 2;
                System.out.printf("%15.6E", x);
                System.out.printf("%15s", "");
                System.out.printf("%15.6E", grid.func(x));
                System.out.printf("%15.6E", l.polynomial.findPolyByHorner(x));
                System.out.printf("%15.6E", n.polynomial.findPolyByHorner(x));
                System.out.println();
            }
        }
    }
}
