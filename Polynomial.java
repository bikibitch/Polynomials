
public class Polynomial {

    private Monomial head;


    private static class Monomial {
        private double index;
        private final int power;
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

        if (this.head == null) {
            this.head = pol.head;
            return;
        }
        else
            if (pol.head == null)
                return;

        Monomial resultHead = null, result = null, cur = null;
        Monomial first = head.power >= pol.head.power ? head : pol.head;
        Monomial second = first == head ? pol.head : head;

        while (true){

            if (first == null){
                if (second != null) {
                    if (resultHead == null)
                        head = null;
                    else {
                        cur = second;
                        result.next = cur;
                    }
                }
                break;
            }
            else if (second == null){
                if (result == null)
                    head = null;
                else {
                    cur = first;
                    result.next = cur;
                }
            }
            else if (first.power > second.power) {
                cur = new Monomial(first.index, first.power);
                first = first.next;
            }
            else if (first.power < second.power) {
                cur = new Monomial(second.index, second.power);
                second = second.next;
            }
            else {
                if (Math.abs(first.index + second.index) > 0.000001){
                    cur = new Monomial(first.index + second.index, first.power);
                    first = first.next;
                    second = second.next;
                }
                else {
                    first = first.next;
                    second = second.next;
                    continue;
                }
            }

            if (result == null) {
                resultHead = cur;
                result = resultHead;
            }
            else {
                result.next = cur;
                result = cur;
            }
        }
        head = resultHead;
    }

    public void multiply(Polynomial pol) {

        if (head == null) {
            head = pol.head;
            return;
        }
        else if (pol.head == null)
            return;
        Monomial resultHead = new Monomial(head.index * pol.head.index, head.power + pol.head.power);
        Monomial cur = resultHead, prev = cur;
        Monomial temp1 = head;
        Monomial temp2 = pol.head.next;
        Monomial temp3;
        while (temp1 != null) {
            while (temp2 != null) {
                temp3 = new Monomial(temp1.index * temp2.index, temp1.power + temp2.power);
                if (cur.power == temp3.power) {
                    if (Math.abs(cur.index + temp3.index) < 0.000001) {
                        if (cur.next == null) {
                            cur = prev;
                            prev.next = null;
                        }
                        else {
                            cur = cur.next;
                            prev.next = cur;
                        }
                    }
                    else
                        cur.index += temp3.index;

                }
                else if (cur.next == null) {
                    cur.next = temp3;
                }
                else if (cur.next.power < temp3.power) {
                    temp3.next = cur.next;
                    cur.next = temp3;
                }
                else {
                    prev = cur;
                    cur = cur.next;
                    continue;
                }
                temp2 = temp2.next;

            }
            temp1 = temp1.next;
            temp2 = pol.head;
            cur = resultHead;
            prev = cur;
        }
        head = resultHead;
    }

    public void multiply(double number) {
        if (Math.abs(number) < 0.000001) {
            head = null;
            return;
        }
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
        int counter = head.power;
        while (counter >= 0) {
                if (temp == null || temp.power < counter) {
                    b = b * x;
                }
                else {
                    b = temp.index + b * x;
                    temp = temp.next;
                }
            counter--;
        }
        return b;
    }

    public void print() {
        Monomial temp = head;
        while (temp != null) {
            if (temp.index < 0) {
                System.out.printf("- %.6E*x^%d ", Math.abs(temp.index), temp.power);
            }
            else
                if (temp == head) {
                    System.out.printf("%.6E*x^%d ", temp.index, temp.power);
                }
                else {
                    System.out.printf("+ %.6E*x^%d ", temp.index, temp.power);
                }
            temp = temp.next;
        }
        System.out.println();
    }
}
