package Dados3;

abstract class VotoConstants extends ListaConstants{
	static public final String VOTOS_TABELA = "Votos";
	static public final String VOTOS_ID = "idVotos"; // PK INT
        static public final String VOTOS_ELEICAO = "Eleicoes_idEleicao"; // FK INT
        static public final String VOTOS_LISTAS = "Lista_idLista"; // FK INT

        static public final String ELEICOES_TABELA = "Eleicoes";
        static public final String ELEICOES_ID = "idEleicao"; // PK INT
    }