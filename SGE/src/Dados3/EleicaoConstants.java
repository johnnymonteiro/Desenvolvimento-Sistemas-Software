package Dados3;

abstract class EleicaoConstants extends VotoConstants {
    static public final String ELEICOES_TABELA = "Eleicoes";
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
    static public final String ELEICOES_RESULTADO = "historico"; // LONGTEXT 
    static public final String ELEICOES_RESULTADO2 = "historico2"; // LONGTEXT 
    
    static public final String TIPOELEICOES_TABELA = "TipoEleicoes";
    static public final String TIPOELEICOES_ID = "idTipoEleicoes"; // PK INT
    static public final String TIPOELEICOES_NOME = "designacao"; // TINYTEXT
    
    static public final String ESTADO_TABELA = "Estado";
    static public final String ESTADO_ID = "idEstado"; // PK INT
    static public final String ESTADO_NOME = "designacaoE"; // TINYTEXT
    
    static public final String REGIAO_TABELA = "Regiao";
    static public final String REGIAO_ID = "idRegiao"; // PK INT
    static public final String REGIAO_NOME = "designacao"; // TINYTEXT
      
}