public class Polynomial {

    private Monomial head;


    private class Monomial {
        private double index;
        private int power;
        private Monomial next;

        Monomial(double index, int power) {
            this.index = index;
            this.power = power;
            this.next = null;
        }
    }

    Polynomial() {
        head = null;
    }

    Polynomial(double index, int power) {
        head = new Monomial(index, power);
    }


    public void add (Polynomial pol) {
        if (head == null) {
            head = pol.head;
            return;
        }
        else
            if (pol.head == null)
                return;
        Monomial result = null, cur = null;
        Monomial temp1 = head.power >= pol.head.power ? head : pol.head;
        Monomial temp2 = temp1 == head ? pol.head : head;
        Monomial temp3;

        while (temp1 != null || temp2 != null) {
            if (temp1 == null || temp1.power < temp2.power) {
                temp3 = new Monomial(temp2.index, temp2.power);
                temp2 = temp2.next;
            }
            else if (temp1.power > temp2.power) {
                    temp3 = new Monomial(temp1.index, temp1.power);
                    temp1 = temp1.next;
                }
            else {
                temp3 = new Monomial(temp1.index + temp2.index, temp1.power);
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            if (cur == null) {
                result = temp3;
                cur = result;
            }
            else {
                cur.next = temp3;
                cur = temp3;
            }
        }
        head = result;
    }

    public void multiply(Polynomial pol) {
        if (head == null) {
            head = pol.head;
            return;
        }
        else if (pol.head == null)
            return;
        Monomial result = null, cur = null;
        Monomial temp1 = head;
        Monomial temp2 = pol.head;
        Monomial temp3;
        while (temp1 != null) {
            while (temp2 != null) {
                temp3 = new Monomial(temp1.index * temp2.index, temp1.power + temp2.power);
                if (cur == null) {
                    result = temp3;
                    cur = result;
                }
                else if (cur.power == temp3.power) {
                    cur.index += temp1.index * temp2.index;
                }
                else if (cur.next == null) {
                    cur.next = temp3;
                }
                else if (cur.next.power < temp3.power) {
                    temp3.next = cur.next;
                    cur.next = temp3;
                }
                else {
                    cur = cur.next;
                    continue;
                }
                temp2 = temp2.next;

            }
            temp1 = temp1.next;
            temp2 = pol.head;
            cur = result;
        }
        head = result;
    }

    public void multiply(double number) {
        Monomial temp = head;
        if (temp == null)
            return;
        while (temp != null) {
            temp.index *= number;
            temp = temp.next;
        }
    }

    public double findPolyByHorner(double x) {
        Monomial temp = head;
        double b = 0;
        while (temp != null) {
                b = temp.index + b * x;
                temp = temp.next;
            }
        return b;
    }

    public void print() {
        Monomial temp = head;
        while (temp != null) {
            if (temp.index < 0)
                System.out.printf("- %.6E*x^%d ", Math.abs(temp.index), temp.power);
            else
                if (temp == head)
                    System.out.printf("%.6E*x^%d ", temp.index, temp.power);
                else
                    System.out.printf("+ %.6E*x^%d ", temp.index, temp.power);
            temp = temp.next;
        }
        System.out.println();
    }
}
