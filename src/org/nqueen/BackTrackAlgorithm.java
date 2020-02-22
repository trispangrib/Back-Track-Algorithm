/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nqueen;

import java.util.ArrayList;
import java.util.List;

public class BackTrackAlgorithm {

    private static int nQueen;
    private static int[] hasil;
    int iterator = 1;
    static int jumlahIterasi, jumlahSolusi, jumlahSimpulMati,
            jumlahAnak;
    static List<String> listSimpulMati, listSimpulSolusi;
    static String judul;

    public BackTrackAlgorithm(int nQueen) {
        this.nQueen = nQueen;
        this.hasil = new int[nQueen + 1];
        jumlahIterasi = jumlahSolusi = jumlahSimpulMati
                = jumlahAnak = 0;
        listSimpulMati = new ArrayList<>();
        listSimpulSolusi = new ArrayList<>();
        judul = "BackTrack N = " + nQueen + " Information";
    }

    private boolean check(int i, int j) {
        jumlahIterasi++;
        if (i == j || (hasil[i] != hasil[j] && Math.abs(i - j)
                != Math.abs(hasil[i] - hasil[j]) && check(i + 1, j)
                && (hasil[j] > 0 && hasil[i] <= nQueen))) {
            return true;
        } else {
            return false;
        }
    }

    void takePlace(int i) {
        if (i == 1) {
            if (hasil[i] == 0) {
                hasil[i] = 1;
            } else {
                hasil[i] += 1;
            }
            jumlahIterasi++;
            return;
        } else if (i > 1 && i <= nQueen) {
            if (hasil[i] == 0) {
                for (int j = 1; j <= nQueen; j++) {
                    jumlahIterasi++;
                    hasil[i] = j;
                    if (check(1, i)) {
                        return;
                    }
                }
                hasil[i] = nQueen + 1;
                return;
            } else {
                for (int j = hasil[i] + 1; j <= nQueen; j++) {
                    jumlahIterasi++;
                    hasil[i] = j;
                    if (check(1, i)) {
                        return;
                    }
                }
                hasil[i] = nQueen + 1;
                return;
            }
        } else {
            jumlahIterasi++;
            hasil[i] = nQueen + 1;
            return;
        }
    }

    void cleanRoute(int i) {
        for (int j = i; j <= nQueen; j++) {
            jumlahIterasi++;
            hasil[j] = 0;
        }
    }

    public int[] routeUseBackTrack() {
        jumlahAnak = 0;

        if (iterator > nQueen) {
            iterator = nQueen;
        }
        while (iterator <= nQueen) {
            if (iterator == 1) {
                takePlace(iterator);
                cleanRoute(iterator + 1);
                iterator++;
                jumlahIterasi++;
            } else if (iterator <= nQueen) {
                if (hasil[iterator] > nQueen) {
                    cleanRoute(iterator);
                    iterator--;
                    listSimpulMati.add(toStringFromListInt());
                    jumlahSimpulMati++;
                    jumlahIterasi++;
                    takePlace(iterator);
                } else if (hasil[iterator] <= nQueen) {
                    takePlace(iterator);
                }
                if (check(1, iterator) && hasil[iterator]
                        <= nQueen) {
                    cleanRoute(iterator + 1);
                    iterator++;
                    jumlahIterasi++;
                } else {
                    iterator--;
                    listSimpulMati.add(toStringFromListInt());
                    jumlahSimpulMati++;
                    jumlahIterasi++;
                }
            }
            if (hasil[1] > nQueen) {
                return null;
            }
        }
        jumlahSolusi++;
        jumlahAnak = jumlahSolusi + jumlahSimpulMati;
        listSimpulSolusi.add(toStringFromListInt());
        return hasil;
    }

    String toStringFromListInt() {
        String hasilString = new String();
        for (int i = 1; i <= nQueen; i++) {
            if (hasil[i] > nQueen || hasil[i] == 0) {
                hasilString += 'x';
            } else {
                hasilString += hasil[i];
            }
        }
        return hasilString;
    }

    public static String getInformation() {
        String info = new String();
        info = judul + "\n"
                + "=======================================\n"
                + "Jumlah Iterasi : " + jumlahIterasi
                + "\n"
                + "Jumlah Solusi : " + jumlahSolusi
                + "\n"
                + "Jumlah Simpul Mati : " + jumlahSimpulMati
                + "\n"
                + "Jumlah Anak : " + jumlahAnak + "\n"
                + "=======================================\n";
        info += "Simpul Mati : \n";
        for (String simpulMati : listSimpulMati) {
            info += simpulMati + "\n";
        }
        info += "=======================================\n";
        info += "Solusi : \n";
        for (String solusiString : listSimpulSolusi) {
            info += solusiString + "\n";
        }
        return info;
    }
}
