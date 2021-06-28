public class Lagrange {
    Polynomial polynomial = new Polynomial();

    Lagrange(Grid grid) {
        for (int i = 0; i < grid.n; i++) {
            Polynomial cur = new Polynomial();
            Polynomial binomial;
            for (int j = 0; j < grid.n; j++) {
                if (j == i)
                    continue;
                binomial = new Polynomial(1, 1);
                binomial.add(new Polynomial(- grid.array[0][j], 0));
                binomial.multiply(1 / (grid.array[0][i] - grid.array[0][j]));
                cur.multiply(binomial);
            }
            cur.multiply(grid.array[1][i]);
            polynomial.add(cur);
        }
    }
}
