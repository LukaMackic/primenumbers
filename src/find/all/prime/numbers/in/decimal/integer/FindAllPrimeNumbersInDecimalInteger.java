/*
Create a function that takes an integer argument and returns an array of prime numbers found in the decimal representation of that number (not factors).

For example, extractPrimes(1717) returns [7, 7, 17, 17, 71].

The array should be in acending order. If a prime number appears more than once, every occurance should be listed. If no prime numbers are found, return an empty array.

Examples
extractPrimes(1) ➞ []

extractPrimes(7) ➞ [7]

extractPrimes(73) ➞ [3, 7, 73]

extractPrimes(1313) ➞ [3, 3, 13, 13, 31, 131, 313]
Notes
All test cases are positive real integers.
Some numbers will have leading zeros. For example, the number 103 contains the prime number 3, but also contains 03. 
These should be treated as the same number, so the result would simply be [3].
 */
package find.all.prime.numbers.in.decimal.integer;

import java.util.ArrayList;
import java.util.Arrays;

public class FindAllPrimeNumbersInDecimalInteger {

    public static void main(String[] args) {

        int[] niz = extractPrimes(1717);
        for (int i = 0; i < niz.length; i++) {
            System.out.println(niz[i]);
        }
    }

    public static int[] extractPrimes(int num) {

        int pomocni = num;
        //velicina broja 
        int n = 0;
        while (pomocni > 0) {
            n++;
            pomocni /= 10;
        }
        // niz cifara 
        int[] nizCifara = new int[n];
        pomocni = num;
        for (int i = 0; i < n; i++) {
            nizCifara[i] = pomocni % 10;
            pomocni /= 10;
        }
        // lista svih brojeva koji se nalaze u glavnom broju 
        ArrayList<Integer> rep = new ArrayList();
        rep.add(num);
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j < n; j++) {
                int broj = 0;
                boolean kontrolna = true;
                for (int m = 0; m < i; m++) {  // izbacivanje suvisnih brojeva tipa 03  ili 034
                    if (m == 0 && nizCifara[j - m] == 0) {
                        kontrolna = false;
                    }
                    broj += nizCifara[j - m];
                    broj *= 10;
                }
                if (kontrolna && broj > 10) {
                    rep.add(broj / 10);
                }
            }
        }
        // filtriranje prostih brojeva
        ArrayList<Integer> nizProstih = new ArrayList();
        for (int i = 0; i < rep.size(); i++) {
            int brojac = 0;
            for (int j = 2; j < rep.get(i) / 2 + 1; j++) {
                if (rep.get(i) % j == 0) {
                    brojac++;
                }
                if (brojac > 0) {
                    break;
                }
            }
            if (brojac == 0 && rep.get(i) > 1) {
                nizProstih.add(rep.get(i));
            }
        }

        //kreiranje niza od ArrayListe
        int[] povratni = new int[nizProstih.size()];
        for (int i = 0; i < nizProstih.size(); i++) {
            povratni[i] = nizProstih.get(i);
        }
        //sortiranje povratnog niza
        Arrays.sort(povratni);

        return povratni;
    }
}
