import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Grid {
    double[][] array;
    double step;
    double a, b;
    int n;

    public void init(String s) throws FileNotFoundException {
        File file = new File(s);
        Scanner scan = new Scanner(file);
        Pattern pat = Pattern.compile("[ \t]+");
        String str = scan.nextLine();
        String[] sn = pat.split(str);
        n = Integer.parseInt(sn[0]);
        a = Double.parseDouble(sn[1]);
        b = Double.parseDouble(sn[2]);
        scan.close();
        create(n);
        array[0][0] = a;
        array[1][0] = func(a);
        step = (b - a) / (n - 1);
        for (int i = 1; i < n; i++) {
            array[0][i] = array[0][i - 1] + step;
            array[1][i] = func(array[0][i]);
        }
    }

    public void create(int n) {
        array = new double[2][];
        for (int i = 0; i < 2; i++)
            array[i] = new double[n];
    }

    double func(double x) {
        return (5 * Math.pow(x,4) - 3 * Math.pow(x,2) + 6);
    }
}
