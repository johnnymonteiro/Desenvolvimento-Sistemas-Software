package Negocio3;
// URLs de REFERENCIA
// http://www.cne.pt/content/metodo-de-hondt
// https://en.wikipedia.org/wiki/Highest_averages_method
// http://www.cne.pt/news/eleicao-ar-2015-mapa-de-deputados-e-sua-distribuicao-pelos-circulos-eleitorais_5616

public interface Hondt {

        // matriz resultado em que cada coluna corresponde ao nº de deputados eleitos
    default public int[] resultados(double[] votos, int deputados) {
            int[] aux = new int[votos.length];
            // matriz auxiliar
            double [][] dhondtTable = new double [deputados][votos.length];
            try{
                for (int m = 0; m < deputados; m++) {
                    for (int n = 0; n < votos.length; n++) {
                        if (m == 0)
                            dhondtTable[m][n] = votos[n];
                        else
                            dhondtTable[m][n] = dhondtTable[0][n]/(m+1);
                    }
                }
                // matriz resultados
                int o;
                for (int c= 1; c < deputados+1; c++) {
                    o = getMaxElem(dhondtTable,votos.length, deputados);
                    aux[o] = aux[o] + 1;
                }
            }
            catch (ArrayIndexOutOfBoundsException error){
                System.err.println(error.getMessage());
            }
            return aux;
	}

        // obtem o maior valor do array 2D (tabela) e remove-o;
        // retorna ainda o partido a qual pertence, ou seja a linha da matriz correspondente.
    default public int getMaxElem(double[][] dTable, int pN, int pM) {
	    double maxVal = dTable[0][0];
            int i =0, j =0;
            try{
                for (int auxn = 0; auxn < pN; auxn++) {
                    for (int auxm = 0; auxm < pM; auxm++) {
                        if(dTable[auxm][auxn] > maxVal){
                            maxVal = dTable[auxm][auxn];
                            i = auxm;
                            j = auxn;
                        }
                    }
                }
                dTable[i][j] = 0.0; // zero é o maior elemento da proxima interacção
            }
            catch (ArrayIndexOutOfBoundsException error){
                System.err.println(error.getMessage());
            }
            return j;
	}
}