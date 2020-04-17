package Dados3;

/**
 *
 * @author willi
 */
abstract class ListaConstants extends EleitorConstants{
    
    /*static public final String ELEICOES_TABELA = "Eleicoes";
    static public final String ELEICOES_ID = "idEleicao"; // PK INT
    static public final String ELEICOES_DT_I = "dt_inicio"; // DATE
    static public final String ELEICOES_DT_F = "dt_fim"; // DATE
    static public final String ELEICOES_DT_IC = "dt_abertura_cand"; // DATE
    static public final String ELEICOES_DT_FC = "dt_fecho_cand"; // DATE
    static public final String ELEICOES_DT_E = "dt_eleicao"; // DATE
    static public final String ELEICOES_FASE = "fase"; // FK INT
    static public final String ELEICOES_AUTONOMA = "regiao_autonoma"; // INT
    static public final String ELEICOES_TIPO = "TipoEleicoes_idTipoEleicoes"; // FK INT
    static public final String ELEICOES_VOTOS_B = "votos_brancos"; // INT
    static public final String ELEICOES_VOTOS_N = "votos_nulos"; // INT
    static public final String ELEICOES_VOTOS_A = "votos_abstencao"; // INT
    static public final String ELEICOES_ESTADO = "estado"; // FK INT

    
    static public final String TIPOELEICOES_TABELA = "TipoEleicoes";
    static public final String TIPOELEICOES_ID = "idTipoEleicoes"; // PK INT
    static public final String TIPOELEICOES_NOME = "designacao"; // TINYTEXT
    
    static public final String ESTADO_TABELA = "Estado";
    static public final String ESTADO_ID = "idEstado"; // PK INT
    static public final String ESTADO_NOME = "designacao"; // TINYTEXT
    
    static public final String REGIAO_TABELA = "Regiao";
    static public final String REGIAO_ID = "idRegiao"; // PK INT
    static public final String REGIAO_NOME = "designacao"; // TINYTEXT
    
    
    // PARTIDOS
    static public final String PARTIDOS_TABELA = "Partidos";
    static public final String PARTIDOS_ID = "idPartidos"; // (PK) INT
    static public final String PARTIDOS_NOME = "designacao"; // TINYTEXT
    static public final String PARTIDOS_PW = "codigo_secreto"; // TINYTEXT
    
    // LISTAS
    static public final String LISTAS_TABELA = "Lista";
    static public final String LISTAS_ID = "idLista"; // (PK) INT
    static public final String LISTAS_PARTIDO = "Partidos_idPartidos"; // (FK) INT
    static public final String LISTAS_CABECA = "cabeça_lista"; // (PK) (FK) INT
    static public final String LISTAS_ELEICAO = "Eleicoes_idEleicao"; // (FK) INT
    static public final String LISTAS_ESTADO = "estadoL"; // BOOL*/

    // RELAÇÃO CANDIDATO(ELEITOR) C/ LISTAS
    static public final String LISTAC_TABELA = "ListaCandidatos";
    static public final String LISTAC_CANDIDATO = "Eleitores_idEleitor"; // (FK) INT
    static public final String LISTAC_LISTA_ID = "Lista_idLista"; // (FK) INT 
    static public final String LISTAC_LISTA_CABECA = "Lista_cabeça_lista"; // (FK) INT
   
   /* // REQUISITOS
    static public final String REQUISITOS_TABELA = "RequisitosPartidos";
    static public final String REQUISITOS_ID = "Partidos_idPartidos"; // PK & FK INT
    static public final String REQUISITOS_1 = "requisito1"; // TEXT
    static public final String REQUISITOS_2 = "requisito2"; // TEXT
    static public final String REQUISITOS_3 = "requisito3"; // TEXT
    
    //CIRCULOS ELETORAL
    static public final String CIRCULO_TABELA = "CirculoEleitoral";
    static public final String CIRCULO_ID = "idCirculoEleitoral"; // (PK) INT
    static public final String CIRCULO_NOME = "designacao"; // TEXT
    static public final String CIRCULO_CADEIRAS = "deputados"; // INT*/
    
     // PARTIDOS
    static public final String PARTIDOS_TABELA = "Partidos";
    static public final String PARTIDOS_ID = "idPartidos"; // (PK) INT
    static public final String PARTIDOS_NOME = "designacao"; // TINYTEXT
    static public final String PARTIDOS_PW = "codigo_secreto"; // TINYTEXT
    
    // LISTAS
    static public final String LISTAS_TABELA = "Lista";
    static public final String LISTAS_ID = "idLista"; // (PK) INT
    static public final String LISTAS_PARTIDO = "Partidos_idPartidos"; // (FK) INT
    static public final String LISTAS_CABECA = "cabeça_lista"; // (PK) (FK) INT
    static public final String LISTAS_ELEICAO = "Eleicoes_idEleicao"; // (FK) INT
    static public final String LISTAS_ESTADO = "estadoL"; // BOOL
    
    static public final String ELEICOES_TABELA = "Eleicoes";
    static public final String ELEICOES_ID = "idEleicao"; // PK INT
}
