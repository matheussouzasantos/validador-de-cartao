import java.util.Scanner;
public class verificadorCartao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int qtdDig = 0;
        long numCartao, copiaCartao;
        boolean valido = false;
        String empresa = "";

        System.out.print("Digite o número do cartao: ");
        numCartao = sc.nextLong();
        copiaCartao = numCartao;
        while (copiaCartao > 0) {
            copiaCartao /= 10;
            qtdDig++;
        }
        copiaCartao = numCartao;
        int[] arrayCartao = new int[qtdDig];

        for (int i = qtdDig - 1; i >= 0; i--) {
            arrayCartao[i] = (int)(copiaCartao % 10);
            copiaCartao /= 10;
        }

        /* Algoritmo de Lunh */
        int somaTotal = 0;
        for (int i = qtdDig - 2; i >= 0; i -= 2) {
            int somaDigitos = 0;
            somaDigitos = arrayCartao[i] * 2;
            if (somaDigitos > 9) {
                int j, k;
                j = somaDigitos / 10;
                k = somaDigitos % 10;
                somaDigitos = j + k;
            }
            somaTotal += somaDigitos;
        }

        for (int i = qtdDig - 1; i >= 0; i -= 2) {
            somaTotal += arrayCartao[i];
        }

        if (somaTotal % 10 == 0) {
            valido = true;
        }

        /* Verificando empresa */
        if (qtdDig == 16) {
            if (arrayCartao[0] == 4) {
                empresa = "Visa";
            }
            else {
                if (arrayCartao[0] == 5) {
                    if (arrayCartao[1] == 1 || arrayCartao[1] == 2 || arrayCartao[1] == 3 || arrayCartao[1] == 4 || arrayCartao[1] == 5)
                    empresa = "MasterCard";
                }
            }
        }
        else {
            if (qtdDig == 15) {
                if (arrayCartao[0] == 3) {
                    if (arrayCartao[1] == 4 || arrayCartao[1] == 7) {
                        empresa = "American Express";
                    }
                }
            }
            else {
                if (qtdDig == 13) {
                    if (arrayCartao[0] == 4) {
                        empresa = "Visa";
                    }
                }
            }
        }

        if (valido == true) {
            System.out.print(empresa);
        }
        else {
            System.out.print("Inválido");
        }
    }
}