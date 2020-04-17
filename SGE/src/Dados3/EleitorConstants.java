package Dados3;

abstract class EleitorConstants extends Database {
    
    // SESSAO
    static public final String UTILIZADORES_TABELA = "Sessoes";
    static public final String UTILIZADORES_ID = "idSessao"; // PK INT
    static public final String UTILIZADORES_DT_I = "dt_inicio"; // DATETIME
    static public final String UTILIZADORES_DT_F = "dt_fim"; // DATETIME
    static public final String UTILIZADORES_ELEITOR = "Eleitores_idEleitor"; // FK INT
    // ELEITORES
    static public final String ELEITORES_TABELA = "Eleitores"; 
    static public final String ELEITORES_ID = "idEleitor"; // PK INT
    static public final String ELEITORES_ADMIN = "admin"; // BOOL
    static public final String ELEITORES_NOME = "nome"; // TEXT
    static public final String ELEITORES_CC = "cartao_cidadao"; // INT
    static public final String ELEITORES_NOME_C = "nome_candidato"; // TEXT
    static public final String ELEITORES_CANDIDATO = "candidato"; // BOOL
    static public final String ELEITORES_DT_N = "dt_nasc"; // DATE
    static public final String ELEITORES_CIRCULO = "Circulo_idCirculoEleitoral"; //FK INT
    static public final String ELEITORES_VOTO = "voto"; // BOOL
    static public final String ELEITORES_CIRCULO_PROVISORIO = "ilha"; // INT
    // REQUISITOS
    static public final String REQUISITOS_TABELA = "Requisitos";
    static public final String REQUISITOS_PARTIDO_TABELA = "RequisitosPartidos";
    static public final String REQUISITOS_ID = "Eleitores_idEleitor"; // PK & FK INT
    static public final String REQUISITOS_PARTIDO_ID = "Partidos_idPartidos"; // PK & FK INT
    static public final String REQUISITOS_1 = "requisito1"; // TEXT
    static public final String REQUISITOS_2 = "requisito2"; // TEXT
    static public final String REQUISITOS_3 = "requisito3"; // TEXT
    // CIRCULO ELEITORAL
    static public final String CIRCULO_TABELA = "CirculoEleitoral";
    static public final String CIRCULO_ID = "idCirculoEleitoral"; // PK INT
    static public final String CIRCULO_NOME = "designacao"; // TEXT
    static public final String CIRCULO_CADEIRAS = "deputados"; // INT
    
    
    
    
}