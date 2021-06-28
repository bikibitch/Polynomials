public class Newton {
    Polynomial polynomial;

    Newton(Grid grid) {
        double[][] table = new double[grid.n + 1][grid.n];
        table[0] = grid.array[1];
        for (int i = 1; i < grid.n; i++) {
            for (int j = 0; j < grid.n - i ; j++) {
                table[i][j] = table[i - 1][j + 1] - table[i - 1][j];
            }
        }
        polynomial = new Polynomial(grid.array[1][0], 0);
        for (int i = 0; i < grid.n - 1; i++) {
            Polynomial cur = new Polynomial();
            for (int j = 1; j <= i + 1; j++) {
                Polynomial binomial = new Polynomial();
                binomial.add(new Polynomial(1, 1));
                binomial.add(new Polynomial(- grid.array[0][j - 1], 0));
                binomial.multiply(1.0 / ((j) * grid.step));
                cur.multiply(binomial);
            }
            cur.multiply(table[i + 1][0]);
            polynomial.add(cur);
        }

    }
}
