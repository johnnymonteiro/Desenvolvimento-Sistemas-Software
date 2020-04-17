package Negocio3;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Facade implements Hondt{
    private final Eleicao eleicao;
    private final Eleitor eleitor; 
    //GestorDeEleicao gestor;
    //Vencedor vencedor;
    private final Voto voto; 
    private final Lista lista;
    //Collection<Eleitor> eleitores;
    //Collection<Eleicao> eleicoes;
    private final Map<Integer,Eleitor> logins; //idEleitor,acesso
   
    
    
    public Facade(){
        this.eleicao = new Eleicao();
        this.eleitor = new Eleitor();
        //this.gestor = new GestorDeEleicao();
        //this.vencedor = new Vencedor();
        // this.eleitores = eleitor.values();
       // this.eleicoes = eleicao.values();
        this.voto = new Voto();
        this.lista = new Lista();
        this.logins = new HashMap<>();
    }
    
    
    
    public boolean connectionIsClosed(){
        return this.eleitor.connectionIsClosed();
    }
     
     public int loginCandidato(String cartao_cidadao){
        int res;
        try{
            int id = eleitor.getIdEleitor(cartao_cidadao);
            Candidato novo = eleitor.loginComoCandidato(id);
            res = id;
        }
        catch(Exception e){
            res = 0;
        }
        return res;
    }
     
     public boolean containsEleitor(Object key){
        return this.eleitor.containsEleitor(key);
    }
     
     public boolean listIsNotFull(int idPartido, int idLista){
        return this.lista.listIsNotFull(idPartido, idLista);
    }
     
     public void login(String cartao_cidadao, int modo_acesso){
        int id = eleitor.getIdEleitor(cartao_cidadao);
        if (id == -1){
            System.err.println("Dados inválidos");
        }
        else {
            Eleitor eaux = eleitor.get(id);
            
            if (logins.containsKey(id)){
                System.err.println("Utilizador já efectou login");
            }
            else {
                if (eaux.isAdmin() && modo_acesso == 0){
                    // JANELA ADMIN
                    logins.put(id, eaux);
                } else {
                if (eaux.isCandidato() && modo_acesso == 1){
                    // JANELA CANDIDATO
                    logins.put(id, eaux);

                } else {
                   // JANELA ELEITOR
                   logins.put(id, eaux);
                    }
                }
            }
        }
    }
     
     public void logout(String cartao_cidadao){
        // candidato em processo de candidatura || eleitor em processo de voto
        logins.remove(eleitor.getIdEleitor(cartao_cidadao));
    }
     
     public void criarEleicao(int idFase, int regiaoAutonoma, LocalDate dt_IniE, LocalDate dt_IniC, LocalDate dt_fimC, int idTipo){
        int idEleicao = eleicao.size()+1;
        LocalDate dt_IniF = LocalDate.now();
       
        //int[] resultadoVotos, Map<Integer, Vencedor> vencedores, Map<Integer, GestorDeEleicao> admins, Map<Integer, Eleitor> eleitores, Map<Integer, Voto> votos) {
        int[] resultados = {0,0,0};
        Map<Integer, Vencedor> vnc= new HashMap<>();
        Map<Integer, Eleitor> els = new HashMap<>();
        Map<Integer,Voto> vts = new HashMap<>();
        int estado = 1;
        Eleicao res = new Eleicao(idEleicao, dt_IniF, dt_IniF, dt_IniC, dt_fimC, dt_IniE, idFase, regiaoAutonoma, idTipo, resultados, vnc, els, vts,estado);
        eleicao.put(res.getIdEleicao(), res);  
    }
    
     //Criar Lista Eleicoes com base no criterio de dadas fornecido(eleicoes encerradas)
    public Collection eleicoesHistoricoData(int tipo, Date dt_inicio, Date dt_fim){
        return this.eleicao.eleicoesHistoricoData(tipo,dt_inicio,dt_fim);
    }
    
    //Criar Lista Eleicoes novinhas(recém criadas e com período de candidatura aberta)
    public Collection eleicoesRecemCriadas(){
        return this.eleicao.eleicoesRecemCriadas();
    }
    
    //Criar Lista Eleicoes com periodo candidatura em aberto
    public Collection eleicoesAbertas(){
        return this.eleicao.eleicoesAbertas();
    }
    
    
    // Usada para fazer String no jList -> ELEICOES ABERTA A VOTOS
    public Collection eleicoesAbertaAVotos(){
        return this.eleicao.eleicoesAbertaAVotos();
    }
    
    //Criar Lista de Eleicoes com periodo de candidatura fechado
    public Collection eleicoesFechadaCandidatura(){
        return this.eleicao.eleicoesFechadaCandidatura();
    }
    
    // Usada para fazer String no jList para fornecer Candidatos
    public Collection getListaCandidatos(int idEleicao){
        return this.eleicao.getListaCandidatos(idEleicao);
    }
    
    //Criar Lista de Partidos de uma dada eleicao
    public Collection getListaPartidos(int idEleicao){
        return this.eleicao.getListaPartidos(idEleicao);
    }
    
    //Collection apenas dos nomes dos Partidos para eleicao em aberto
    public Collection getListaPartidosEleicaoVotos(){
        return this.eleicao.getListaPartidosEleicaoVotos();
    }
    
    //Collection apenas dos nomes dos candidatos nominais para eleicao em aberto
    public Collection getListaNominalEleicaoVotos(){
        return this.eleicao.getListaNominalEleicaoVotos();
    }
    
    //devolve o int (id tipo) correspondente da eleicao
     public int getTipoEleicao(int idEleicao){
         return this.eleicao.getTipoEleicao(idEleicao);
     }    
     
    //Criar entrada na tabela requisitos do eleitor
    public int createPreRequisitos(int idEleitor, String req1, String req2, String req3){
        return eleitor.setPreRequisitos(idEleitor, req1, req2, req3);
    }
    
    //Collection para eleiçoes "prontas" para votos, ou seja, prontos a iniciar
    public Collection eleicoesProntoAVoto(){
        return this.eleicao.eleicoesProntoAVoto();
    }
    
    //Size da tabela Partido
    public int sizePartido(){
        return this.lista.sizePartido();
    }
    
    //Criar partido na BD map<k,v>
    public Partido putPartidoByKey(Integer key, String nome, String codigo){
        return this.lista.putPartidoByKey(key,nome,codigo);
    }
    
    //Criar partido na BD
    //public Partido putPartido(String nome, String codigo_secreto){
    //    return this.listaDB.putPartido(nome,codigo_secreto);
    //}
    
    //Retorna o ID do partido na tabela PARTIDO
    public int getIdPartido(String nomePartido){
        return this.lista.getIdPartido(nomePartido);
    }
    
    //Retorna o boolean relactivo ao já votou do eleitor
    public boolean eleitorVotou(int idEleitor){
        return this.eleitor.getEleitorVoto(idEleitor);
    }
    
    
    //Criar Pre-requisitos dos partidos
    public int createPreRequisitosPartidos(int idPartido, String req1, String req2, String req3){
        return this.lista.setPreRequisitosPartidos(idPartido, req1, req2, req3);
    }
    
    
    //Altera o eleitor para candidato
    public void setCandidato(int idEleitor, boolean estado){
        this.eleitor.setCandidato(idEleitor, estado);
    }
    
    //Altera o estado de voto do Candidato
    public void setEleitorVoto(int idEleitor, boolean voto){
        this.eleitor.setEleitorVoto(idEleitor, voto);
    }
    
    //Altera o nome do Candidato
    public void setNomeCandidato(String Cartao_cidadao, String nome){
        this.eleitor.setNomeCandidato(Cartao_cidadao, nome);
    }
    
    //Altera o nome do Candidato pelo ID
    public void setNomeCandidatoID(int idEleitor, String nome){
        this.eleitor.setNomeCandidatoID(idEleitor, nome);
    }
    
    //Altera o circulo eleitoral do candidato
    public void setCirculoEleitoral(int idEleitor, int circulo){
        this.eleitor.setCirculoEleitoral(idEleitor, circulo);
    }
    
    //Altera provisoriamente o circulo eleitoral do candidato (eleicoes para regioes autonomas)
     public void setCirculoEleitoralProvisorio(int idEleitor, int idIlha){
         this.eleitor.setCirculoEleitoralProvisorio(idEleitor, idIlha);
     }
    
    //Autentica partido
    public boolean autenticaPartido(int idPartido, String codigo_secreto){
        return this.lista.autenticaPartido(idPartido, codigo_secreto);
    }
    
    //Autentica lista
     public boolean autenticaLista(int idPartido, int idLista){
         return this.lista.autenticaLista(idPartido, idLista);
     }
    
    // dado o nome do circulo eleitoral devolve o id
    public int getIdCirculo(String circulo_eleitoral){
        return this.lista.getIdCirculo(circulo_eleitoral);
    }
    
    // adiciona uma entrada na lista
    public void putLista(int idLista, int idPartido, int idCabeca, boolean estado, int idEleicao){
        this.lista.putLista(idLista, idPartido, idCabeca, estado, idEleicao);
    }
    
    // dá o id da eleicao em aberto -1 se não houver APENAS UMA ELEICAO em aberto
    public int idEleicaoAberta(){
        return this.eleicao.idEleicaoAberta();
    }
    
    //retorna o id da eleicão aberta a votos e -1 caso contrario.
    public int idEleicaoAbertaAVtoto(){
        return this.eleicao.idEleicaoAbertaAVtoto();
    }
    
     // devolve -1 se houver erro
    public int sizeLista(int idCabeca){
        return this.lista.sizeLista(idCabeca);
    }
     // Usada para fazer String no jList
    public Collection listaAbertas(int idPartido){
        return this.lista.listaAbertas(idPartido);
    }
    
    //adicionar candiato a uma lista de um dado partido
    public int adicionarCandidatoLista(int idCandidato, int idLista, int idPartido){
        return this.lista.adicionarCandidatoLista(idCandidato, idLista, idPartido);
    }
    
    //devolve um Objecto Eleitor
    public Eleitor getEleitor(int idEleitor){
        return this.eleitor.get(idEleitor);
    }
    
    // altera o estado de uma, e apenas uma lista. 
    public void setListaAprovada(int idLista, boolean estado){
        this.lista.setListaAprovada(idLista, estado);
    }
    
    // aprova TODO um partido, ou seja, todas as suas listas
    public void aprovaPartido(int idEleicao, int idPartido){
        this.lista.aprovaPartido(idEleicao, idPartido);
    }
    
    // aprova o candidato a uma eleição nominal, apenas ele e a sua MONO LISTA!
    public void aprovaCandidato(int idEleicao, int idCandidato){
        // getListaPartidos serve igual aki! nao é typo error
        this.setListaAprovada(idCandidato, true);
    }
    
    // dado o nome do candidato devolve o seu idEleitor para voto correspondente
    public int getIdCandidatoParaVoto(String nomeCandidato){
        return this.lista.getIdCandidatoParaVoto(nomeCandidato);
    }
    
    // devolve a collection<int> ids das listas de um partido
    public Collection getIdListasPartidos(int idEleicao, int idPartido){
        return this.lista.getIdListasPartidos(idEleicao, idPartido);
    }

    //passar o estado de uma eleição para, recém criada, para candidatura aberta
    public void setEstadoEleicaoAbrirCandidatura(int idEleicao){
        this.eleicao.setEstadoEleicaoAbrirCandidatura(idEleicao);
    }
    
    // passar o estado de uma eleiçao, fechada na candidatura, de não validada
    // para validada.
    public void setEstadoEleicaoAprovada(int idEleicao){
        this.eleicao.setEstadoEleicaoAprovada(idEleicao);
    }
    
     // passar o estado de uma eleiçao, aberta na candidatura, para fechada
     public void setEstadoEleicaoFecharCandidatura(int idEleicao){
         this.eleicao.setEstadoEleicaoFecharCandidatura(idEleicao);
     }
     
     // passar o estado de uma eleiçao, aberta na candidatura, para fechada
     public void setEstadoEleicaoAbertaVotos(int idEleicao){
         this.eleicao.setEstadoEleicaoAbertaVotos(idEleicao);
     }
     
     // passar o estado de uma eleicao, aberta a votos, para encerrad
     public void setEstadoEleicaoEncerrada(int idEleicao){
         this.eleicao.setEstadoEleicaoEncerrada(idEleicao);
     }
     
     // devolve dado um eleitor o partido pretendido e a eleicao aberta a voto o id da lista correspondente, ajuda a construir voto
     public int getIdListaParaVoto(int idEleitor, int idEleicao, String nomePartido){
         //collection de ints
          Collection aux = this.lista.getIdListasPartidos(idEleicao, this.getIdPartido(nomePartido));
          
          Eleitor e = this.eleitor.get(idEleitor);
          Eleitor cabeca;
         int res=-1;
         for(Object l : aux){
             cabeca  = this.eleitor.get((int) l);
             if(e.getCirculo().getIdCirculo() == cabeca.getCirculo().getIdCirculo()){
                 res = (int) l;
             }
         }
         return res;
      }
     
     //voto para DB
     public int putVoto(int idEleicao, int idLista){
         return this.voto.putVoto(idEleicao,idLista);
     }
     
     
     public int getIdListaParaVoto(int idEleicao, String partido, int idCirculo){
          return this.lista.getIdListaParaVoto(idEleicao, partido, idCirculo);
      }

    //dado o idEleitor devolve o seu idCirculo correspondente.
     public int getCirculoEleitoralEleitor(int idEleitor){
          return this.eleitor.getCirculoEleitoralEleitor(idEleitor);
     }
    
     //devolve o numero de eleicoes com estad == 1, ou seja, recem criadas
    public int quantasEleicoesPeriodoCandidaturaAberta(){
        return this.eleicao.quantasEleicoesPeriodoCandidaturaAberta();
    }
    
    //RESULTADOS
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaRepublica(int idEleicao){
        return this.voto.resultadosAssembleiaRepublica(idEleicao);
    }
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaAçores(int idEleicao){
        return this.voto.resultadosAssembleiaAçores(idEleicao);
    }
    public Map<Integer,Map<Integer,Integer>> getResultadosAssembleiaMadeira(int idEleicao){
        return this.voto.resultadosAssembleiaMadeira(idEleicao);
    }
    public Map<Integer,Integer> getResultadosPresidencial(int idEleicao){
        return this.voto.resultadosPresidencial(idEleicao);
    }
    
     // Nome do candidato
    public String getNomeCandidato(int idCandidato){
        return this.lista.getNomeCandidato(idCandidato);
    }
    
    //Nome do partido
    public String getNomePartido(int idPartido){
        return this.lista.getNomePartido(idPartido);
    }
    
    //Nome do circulo
    public String geNomeCirculo(int idCirculo){
        return this.lista.geNomeCirculo(idCirculo);
    }
    
    //GERA RESULTADOS
     public int[] getVotosBrancoENulos(int idEleicao){
        return this.eleicao.getVotosBrancoENulos(idEleicao);
    }
     
     // Devolve o numero de partitods para uma dada eleicao
      public int getNumeroPartidos(int idEleicao){
          return this.lista.getNumeroPartidos(idEleicao);
      }
      // devolve o numero de votos efectuados numa eleicao, e que nao sao nulos nem em branco!
      public int votosEfectuadosNaEleicao(int idEleicao){
          return this.voto.votosEfectuadosNaEleicao(idEleicao);
      }
      
      // devolve o numero de votos efectuados numa determinada lista, sem considerar nulos e branco!
      public int votosEfectuadosNaLista(int idLista){
          return this.voto.votosEfectuadosNaLista(idLista);
      }
      
      // devolve o numero de eleitors da BD
      public int numeroEleitores(){
        return this.eleitor.size();
      }
      
      // guarda o resultado da eleição (no caso da Assembleia é o TOTAL)
      public void encerraEleicaoPutHistorico(int idEleicao, String historico){
          this.eleicao.encerraEleicaoPutHistorico(idEleicao, historico);
      }
      // guarda o resultado da eleiçao por circulo eleitoral 
       public void encerraEleicaoPutHistorico2(int idEleicao, String historico2){
          this.eleicao.encerraEleicaoPutHistorico2(idEleicao, historico2);
       }
      
      // guarda o numero de eleitores que não votarem nesta eleição
      public void encerraEleicaoPutAbstencao(int idEleicao, int abstencao){
          this.eleicao.encerraEleicaoPutAbestencao(idEleicao, abstencao);
      }
              
      /* COllection de idCandidatos auxiliar para encerrar eleiçao
      public Collection getIdCandidatosDaEleicaoAEncerrarAssembleia(int idEleicao){
          return this.eleicaoDB.getIdCandidatosDaEleicaoAssembleia(idEleicao);
      }*/
      
       // devolve o nome do eleitor
    public String getNomeEleitor(int idEleitor){
        return this.eleitor.getNomeEleitor(idEleitor);
    }
      
      // CLEAR TABLES
    public void dropPreRequisitoPartido(){
        this.eleicao.dropPreRequisitoPartido();
    }
    public void dropPreRequisito(){
        this.eleicao.dropPreRequisito();
    }
    public void dropPartidos(){
        this.eleicao.dropPartidos();
    }
    public void dropListaCandidatos(){
        this.eleicao.dropListaCandidatos();
    }
    public void dropVotos(){
        this.eleicao.dropVotos();
    }
    public void dropLista(){
        this.eleicao.dropLista();
    }
    
    // LIMPA VOTOS
    public void clearVotos(){
        this.eleitor.clearVotos();
    }
    // IDs Candidatos
    public Collection getIdCandidatosDaEleicaoAssembleia(int idEleicao){
        return this.eleicao.getIdCandidatosDaEleicaoAssembleia(idEleicao);
    }
    public Collection getIdCandidatosDaEleicaoPresidencial(int idEleicao){
        return this.eleicao.getIdCandidatosDaEleicaoPresidencial(idEleicao);
    }

    // Finaliza o estado da base de dados, relativamente aos candidatos e votos
    public void finalizarEleicaoBD(int idEleicao){
          if(this.getTipoEleicao(idEleicao)==1){ // PRESIDENCIAL
              this.dropPreRequisito();
              this.dropVotos();
              Collection candidatos = this.getIdCandidatosDaEleicaoPresidencial(idEleicao);
              for(Object id : candidatos){
                  this.setNomeCandidatoID((int) id, "ELEITOR");
                  this.setCandidato((int) id, false);
              }
              this.clearVotos();
          } else { //ASSEMBLEIAS
              if (this.getTipoEleicao(idEleicao)>1 && this.getTipoEleicao(idEleicao)<5){
                this.dropPreRequisitoPartido();
                this.dropPartidos();
                this.lista.putPartidoByKey(1,"Presidencial",""); // REPORT PARTIDO Default
                this.dropVotos();
                Collection candidatos = this.getIdCandidatosDaEleicaoAssembleia(idEleicao);
                for(Object id : candidatos){
                  this.setNomeCandidatoID((int) id, "ELEITOR");
                  this.setCandidato((int) id, false);
                }
                this.dropListaCandidatos();
                this.dropLista();
                this.clearVotos();
                
                if(this.getTipoEleicao(idEleicao)==3 || this.getTipoEleicao(idEleicao)==4){ // MADEIRA OU AÇORES
                    this.reporCirculoEleitoral();
                }
                    
              } 
          }
      }
    
    public String getHistoricoAssembleiaTotal(int idEleicao){
        return this.eleicao.getHistorico1(idEleicao);
    }
    
    public String getHistoricoAssembleiaPorCirculo(int idEleicao){
        return this.eleicao.getHistorico2(idEleicao);
    }
    
    public String getHistoricoPresidencial(int idEleicao){
        return this.eleicao.getHistorico1(idEleicao);
    }
    public int getCirculoProvisorio(int idEleitor){
        return this.eleitor.getCirculoProvisorio(idEleitor);
    }
    public Collection getIdsVotou(){
        return this.eleitor.getIdsVotou();
    }
    public void clearCirculoProvisorio(int idEleitor){
        this.eleitor.clearCirculoProvisorio(idEleitor);
    }
    public void reporCirculoEleitoral(){
        Collection idsEleitores = this.getIdsVotou();
        for (Object entry : idsEleitores){
            int cir = this.getCirculoProvisorio((int) entry);
            this.setCirculoEleitoral((int) entry, cir);
            this.clearCirculoProvisorio((int) entry);
        }
    }
    
    //PRESIDENCIA
   public String geraResultadoPresidencial(int idEleicao){
         Map<Integer,Integer> resultadoPresidencial = this.voto.getResultadosPresidencial(idEleicao);
         Map<Integer, Integer> sortedMap = resultadoPresidencial.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
     
        int[] aux = this.eleicao.getVotosBrancoENulos(idEleicao);
        int votosTotal = aux[0]+aux[1]+this.voto.votosEfectuadosNaEleicao(idEleicao);
        StringBuilder sbSelect = new StringBuilder();
        int i=0;
        for(Map.Entry<Integer,Integer> entry : sortedMap.entrySet()){
            if(i==0){
                sbSelect.append("O vencendor é o(a) ");
                sbSelect.append(this.lista.getNomeCandidato(entry.getKey()));
                sbSelect.append("\n\n");
                i++;
            }
            sbSelect.append(this.lista.getNomeCandidato(entry.getKey()));
            sbSelect.append(" obteve no total ");
            sbSelect.append(entry.getValue());
            sbSelect.append(" (");
            double percentil = (double) entry.getValue();
            percentil = ((double) percentil /(double) votosTotal)*100;
            sbSelect.append(String.format("%.2f", percentil));
            sbSelect.append("%");
            sbSelect.append(") votos.");
            sbSelect.append("\n");
        }
        
        sbSelect.append("\nHouveram nesta eleição: \n");
            sbSelect.append(aux[0]);
            double aux0 = ((double) aux[0]/(double) votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux0));sbSelect.append("%)");
            sbSelect.append(" votos brancos.\n");
            sbSelect.append(aux[1]);
            double aux1 = ((double)aux[1]/(double)votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux1));sbSelect.append("%)");
            sbSelect.append(" votos nulos.\n");
            int abstencao = this.eleitor.numeroEleitores()-votosTotal;
            sbSelect.append(abstencao);
            double abst = 100-(((double)votosTotal/(double)this.eleitor.numeroEleitores())*100);
            sbSelect.append("(");sbSelect.append(String.format("%.2f", abst));sbSelect.append("%)");
            sbSelect.append(" eleitores abstiveram-se desta eleição.");
            
        //ELEICAO ENCERRA -> SET RESULTADOS
        this.eleicao.encerraEleicaoPutAbstencao(idEleicao, abstencao);
        this.eleicao.encerraEleicaoPutHistorico(idEleicao, sbSelect.toString());
        return sbSelect.toString();       
    }
     
      //ASSEMBLEIA DA REPUBLICA
   public String geraResultadoAssembleiaRepublica(int idEleicao){
        Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaRepublica(idEleicao);
    
            StringBuilder sbSelect = new StringBuilder();
            
            for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){    
                sbSelect.append("\n");
                sbSelect.append("Círculo eleitoral de ");
                // entry.getKey() -> ID DO CIRCULO
                sbSelect.append(this.lista.geNomeCirculo(entry.getKey()));
                sbSelect.append("\n");
                for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                    sbSelect.append("Partido ");
                    // entry2.getKey() -> ID DA LISTA CORRESPONDENTE
                    sbSelect.append(this.lista.getNomePartido((int)entry2.getKey()));
                    sbSelect.append(" tem direito a ");
                    // entry2.getValue() -> Número de cadeiras correspondente (por Hondt)
                    sbSelect.append(entry2.getValue());
                    if (entry2.getValue()>1){
                        sbSelect.append(" cadeiras .\n");
                    }
                    else {
                        sbSelect.append(" cadeira.\n");
                    }
                }
            }
            // IMIPRIME RESULTADO (ESTÀ EM sbSelect :: String)
            //System.out.println(sbSelect);
            return sbSelect.toString();
         }
    
   public String geraResultadoAssembleiaAçores(int idEleicao){
        Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaAçores(idEleicao);
    
            StringBuilder sbSelect = new StringBuilder();
            
            for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){    
                sbSelect.append("\n");
                sbSelect.append("Círculo eleitoral de ");
                // entry.getKey() -> ID DO CIRCULO
                sbSelect.append(this.lista.geNomeCirculo(entry.getKey()));
                sbSelect.append("\n");
                for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                    sbSelect.append("Partido ");
                    // entry2.getKey() -> ID DA LISTA CORRESPONDENTE
                    sbSelect.append(this.lista.getNomePartido((int)entry2.getKey()));
                    sbSelect.append(" tem direito a ");
                    // entry2.getValue() -> Número de cadeiras correspondente (por Hondt)
                    sbSelect.append(entry2.getValue());
                    if (entry2.getValue()>1){
                        sbSelect.append(" cadeiras .\n");
                    }
                    else {
                        sbSelect.append(" cadeira.\n");
                    }
                }
            }
            // IMIPRIME RESULTADO (ESTÀ EM sbSelect :: String)
            //System.out.println(sbSelect);
            return sbSelect.toString();
         }
    
   public String geraResultadoAssembleiaMadeira(int idEleicao){
        Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaMadeira(idEleicao);
    
            StringBuilder sbSelect = new StringBuilder();
            
            for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){    
                sbSelect.append("\n");
                sbSelect.append("Círculo eleitoral de ");
                // entry.getKey() -> ID DO CIRCULO
                sbSelect.append(this.lista.geNomeCirculo(entry.getKey()));
                sbSelect.append("\n");
                for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                    sbSelect.append("Partido ");
                    // entry2.getKey() -> ID DA LISTA CORRESPONDENTE
                    sbSelect.append(this.lista.getNomePartido((int)entry2.getKey()));
                    sbSelect.append(" tem direito a ");
                    // entry2.getValue() -> Número de cadeiras correspondente (por Hondt)
                    sbSelect.append(entry2.getValue());
                    if (entry2.getValue()>1){
                        sbSelect.append(" cadeiras .\n");
                    }
                    else {
                        sbSelect.append(" cadeira.\n");
                    }
                }
            }
            // IMIPRIME RESULTADO (ESTÀ EM sbSelect :: String)
            //System.out.println(sbSelect);
            return sbSelect.toString();
         }
   
   public String geraResultadoTotalAssembleiaRepublica(int idEleicao){
          // CIRUCLO       PARTIDO  CADEIRAS
          Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaRepublica(idEleicao); 
          
          // PARTIDO  CADEIRAS
          Map<Integer,Integer> auxRes = new HashMap<>();

          //Nº partidos
          int nPartidos = this.lista.getNumeroPartidos(idEleicao);
          
          int[] resultado = new int[nPartidos];
          
          int[] aux = this.eleicao.getVotosBrancoENulos(idEleicao);
          int votosTotal = aux[0]+aux[1]+this.voto.votosEfectuadosNaEleicao(idEleicao);
          
          StringBuilder sbSelect = new StringBuilder();
          
                       // CIRCULO IDPARTIDO VALORCADEIRAS
          for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  auxRes.put(entry2.getKey(),0);
              }
          }
          
           for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  int voto = entry2.getValue()+auxRes.get(entry2.getKey());
                  auxRes.put(entry2.getKey(),voto);
              }
          }
          
           Map<String,Integer> cadeirasPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               
               if( cadeirasPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = entry.getValue();
                   somaAux += cadeirasPartido.get(this.lista.getNomePartido(entry.getKey()));
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else {
                   if(entry.getKey() != null)
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), entry.getValue());
               }
           }
           Map<String,Integer> votosNoPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               if(votosNoPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = votosNoPartido.get(this.lista.getNomePartido(entry.getKey()));
                   somaAux += this.voto.votosEfectuadosNaLista(entry.getKey());
                   votosNoPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else{
                   if(entry.getKey() != null){
                       votosNoPartido.put(this.lista.getNomePartido(entry.getKey()), this.voto.votosEfectuadosNaLista(entry.getKey()));
                   }
               }
           }
          // String builder PS tem direto a eleger 2 deputado. Obteve x% dos votos.
          for(Map.Entry<String,Integer> entry : cadeirasPartido.entrySet()){
              sbSelect.append(entry.getKey());
              sbSelect.append(" tem direito a eleger ");
              sbSelect.append(entry.getValue());
              sbSelect.append(" deputados. Obteve ");
              double percentil = (double) votosNoPartido.get(entry.getKey());
              percentil = ((double) percentil /(double) votosTotal)*100;
              
              /*int temp = (int)(percentil * Math.pow(10 , 2));  
              percentil = ((double)temp)/Math.pow(10 , 2);  
              sbSelect.append(percentil);*/
              
              sbSelect.append(String.format("%.2f", percentil));
              sbSelect.append("% dos votos.");
              sbSelect.append ("\n");
          }
            sbSelect.append("\nHouveram nesta eleição: \n");
            sbSelect.append(aux[0]);
            double aux0 = ((double) aux[0]/(double) votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux0));sbSelect.append("%)");
            sbSelect.append(" votos brancos.\n");
            sbSelect.append(aux[1]);
            double aux1 = ((double)aux[1]/(double)votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux1));sbSelect.append("%)");
            sbSelect.append(" votos nulos.\n");
            int abstencao = this.eleitor.numeroEleitores()-votosTotal;
            sbSelect.append(abstencao); 
            double abst = 100-(((double)votosTotal/(double)this.eleitor.numeroEleitores())*100);
            sbSelect.append("(");sbSelect.append(String.format("%.2f", abst));sbSelect.append("%)");
            sbSelect.append(" eleitores abstiveram-se desta eleição.");
      
        //ELEICAO ENCERRA -> SET RESULTADOS
        this.eleicao.encerraEleicaoPutAbstencao(idEleicao, abstencao);
        this.eleicao.encerraEleicaoPutHistorico(idEleicao, sbSelect.toString());
        this.eleicao.encerraEleicaoPutHistorico2(idEleicao, this.geraResultadoAssembleiaRepublica(idEleicao));
        return sbSelect.toString(); 
      }
            
   public String geraResultadoTotalAssembleiaAçores(int idEleicao){
          // CIRUCLO       PARTIDO  CADEIRAS
          Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaAçores(idEleicao); 
          
          // PARTIDO  CADEIRAS
          Map<Integer,Integer> auxRes = new HashMap<>();

          //Nº partidos
          int nPartidos = this.lista.getNumeroPartidos(idEleicao);
          
          int[] resultado = new int[nPartidos];
          
          int[] aux = this.eleicao.getVotosBrancoENulos(idEleicao);
          int votosTotal = aux[0]+aux[1]+this.voto.votosEfectuadosNaEleicao(idEleicao);
          
          StringBuilder sbSelect = new StringBuilder();
          
                       // CIRCULO IDPARTIDO VALORCADEIRAS
          for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  auxRes.put(entry2.getKey(),0);
              }
          }
          
           for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  int voto = entry2.getValue()+auxRes.get(entry2.getKey());
                  auxRes.put(entry2.getKey(),voto);
              }
          }
          
           Map<String,Integer> cadeirasPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               
               if( cadeirasPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = entry.getValue();
                   somaAux += cadeirasPartido.get(this.lista.getNomePartido(entry.getKey()));
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else {
                   if(entry.getKey() != null)
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), entry.getValue());
               }
           }
           Map<String,Integer> votosNoPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               if(votosNoPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = votosNoPartido.get(this.lista.getNomePartido(entry.getKey()));
                   somaAux += this.voto.votosEfectuadosNaLista(entry.getKey());
                   votosNoPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else{
                   if(entry.getKey() != null){
                       votosNoPartido.put(this.lista.getNomePartido(entry.getKey()), this.voto.votosEfectuadosNaLista(entry.getKey()));
                   }
               }
           }
          // String builder PS tem direto a eleger 2 deputado. Obteve x% dos votos.
          for(Map.Entry<String,Integer> entry : cadeirasPartido.entrySet()){
              sbSelect.append(entry.getKey());
              sbSelect.append(" tem direito a eleger ");
              sbSelect.append(entry.getValue());
              sbSelect.append(" deputados. Obteve ");
              double percentil = (double) votosNoPartido.get(entry.getKey());
              percentil = ((double) percentil /(double) votosTotal)*100;
              
              /*int temp = (int)(percentil * Math.pow(10 , 2));  
              percentil = ((double)temp)/Math.pow(10 , 2);  
              sbSelect.append(percentil);*/
              
              sbSelect.append(String.format("%.2f", percentil));
              sbSelect.append("% dos votos.");
              sbSelect.append ("\n");
          }
            sbSelect.append("\nHouveram nesta eleição: \n");
            sbSelect.append(aux[0]);
            double aux0 = ((double) aux[0]/(double) votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux0));sbSelect.append("%)");
            sbSelect.append(" votos brancos.\n");
            sbSelect.append(aux[1]);
            double aux1 = ((double)aux[1]/(double)votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux1));sbSelect.append("%)");
            sbSelect.append(" votos nulos.\n");
            int abstencao = this.eleitor.numeroEleitores()-votosTotal;
            sbSelect.append(abstencao); 
            double abst = 100-(((double)votosTotal/(double)this.eleitor.numeroEleitores())*100);
            sbSelect.append("(");sbSelect.append(String.format("%.2f", abst));sbSelect.append("%)");
            sbSelect.append(" eleitores abstiveram-se desta eleição.");
      
        //ELEICAO ENCERRA -> SET RESULTADOS
        this.eleicao.encerraEleicaoPutAbstencao(idEleicao, abstencao);
        this.eleicao.encerraEleicaoPutHistorico(idEleicao, sbSelect.toString());
        this.eleicao.encerraEleicaoPutHistorico2(idEleicao, this.geraResultadoAssembleiaAçores(idEleicao)); 
        return sbSelect.toString(); 
      }
    
   public String geraResultadoTotalAssembleiaMadeira(int idEleicao){
          // CIRUCLO       PARTIDO  CADEIRAS
          Map<Integer,Map<Integer,Integer>> res = this.voto.getResultadosAssembleiaMadeira(idEleicao); 
          
          // PARTIDO  CADEIRAS
          Map<Integer,Integer> auxRes = new HashMap<>();

          //Nº partidos
          int nPartidos = this.lista.getNumeroPartidos(idEleicao);
          
          int[] resultado = new int[nPartidos];
          
          int[] aux = this.eleicao.getVotosBrancoENulos(idEleicao);
          int votosTotal = aux[0]+aux[1]+this.voto.votosEfectuadosNaEleicao(idEleicao);
          
          StringBuilder sbSelect = new StringBuilder();
          
                       // CIRCULO IDPARTIDO VALORCADEIRAS
          for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  auxRes.put(entry2.getKey(),0);
              }
          }
          
           for(Map.Entry<Integer,Map<Integer,Integer>> entry : res.entrySet()){
              // FOR EACH CIRCULO
              for(Map.Entry<Integer,Integer> entry2 : entry.getValue().entrySet()){
                  // 
                  int voto = entry2.getValue()+auxRes.get(entry2.getKey());
                  auxRes.put(entry2.getKey(),voto);
              }
          }
          
           Map<String,Integer> cadeirasPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               
               if( cadeirasPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = entry.getValue();
                   somaAux += cadeirasPartido.get(this.lista.getNomePartido(entry.getKey()));
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else {
                   if(entry.getKey() != null)
                   cadeirasPartido.put(this.lista.getNomePartido(entry.getKey()), entry.getValue());
               }
           }
           Map<String,Integer> votosNoPartido = new HashMap<>();
           for(Map.Entry<Integer,Integer> entry : auxRes.entrySet()){
               if(votosNoPartido.containsKey(this.lista.getNomePartido(entry.getKey()))){
                   int somaAux = votosNoPartido.get(this.lista.getNomePartido(entry.getKey()));
                   somaAux += this.voto.votosEfectuadosNaLista(entry.getKey());
                   votosNoPartido.put(this.lista.getNomePartido(entry.getKey()), somaAux);
               }
               else{
                   if(entry.getKey() != null){
                       votosNoPartido.put(this.getNomePartido(entry.getKey()), this.voto.votosEfectuadosNaLista(entry.getKey()));
                   }
               }
           }
          // String builder PS tem direto a eleger 2 deputado. Obteve x% dos votos.
          for(Map.Entry<String,Integer> entry : cadeirasPartido.entrySet()){
              sbSelect.append(entry.getKey());
              sbSelect.append(" tem direito a eleger ");
              sbSelect.append(entry.getValue());
              sbSelect.append(" deputados. Obteve ");
              double percentil = (double) votosNoPartido.get(entry.getKey());
              percentil = ((double) percentil /(double) votosTotal)*100;
              
              /*int temp = (int)(percentil * Math.pow(10 , 2));  
              percentil = ((double)temp)/Math.pow(10 , 2);  
              sbSelect.append(percentil);*/
              
              sbSelect.append(String.format("%.2f", percentil));
              sbSelect.append("% dos votos.");
              sbSelect.append ("\n");
          }
            sbSelect.append("\nHouveram nesta eleição: \n");
            sbSelect.append(aux[0]);
            double aux0 = ((double) aux[0]/(double) votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux0));sbSelect.append("%)");
            sbSelect.append(" votos brancos.\n");
            sbSelect.append(aux[1]);
            double aux1 = ((double)aux[1]/(double)votosTotal)*100;
            sbSelect.append("(");sbSelect.append(String.format("%.2f", aux1));sbSelect.append("%)");
            sbSelect.append(" votos nulos.\n");
            int abstencao = this.eleitor.numeroEleitores()-votosTotal;
            sbSelect.append(abstencao); 
            double abst = 100-(((double)votosTotal/(double)this.eleitor.numeroEleitores())*100);
            sbSelect.append("(");sbSelect.append(String.format("%.2f", abst));sbSelect.append("%)");
            sbSelect.append(" eleitores abstiveram-se desta eleição.");
      
        //ELEICAO ENCERRA -> SET RESULTADOS
        this.eleicao.encerraEleicaoPutAbstencao(idEleicao, abstencao);
        this.eleicao.encerraEleicaoPutHistorico(idEleicao, sbSelect.toString());
        this.eleicao.encerraEleicaoPutHistorico2(idEleicao, this.geraResultadoAssembleiaMadeira(idEleicao)); 
        return sbSelect.toString(); 
      }
    
 
   public int getIdEleitor(String cartao_cidadao){
       return this.eleitor.getIdEleitor(cartao_cidadao);
   }
   
   public boolean isAdmin(int idEleitor){
       return this.eleitor.isAdmin(idEleitor);
   }
   
   public void setVotoBranco(int idEleicao){
       this.eleicao.setVotoBranco(idEleicao);
   }
   
   public boolean candidatoJaExiste(int idEleitor){
       return this.eleitor.candidatoJaExiste(idEleitor);
   }
   public boolean partidoJaExiste(String nome){
       return this.lista.partidoJaExiste(nome);
   }
   
    // Usada para obter IDs das eleicoesAbertas
    public Collection eleicoesAbertaID(){
        return this.eleicao.eleicoesAbertaID();
    }
    // Usada para obter IDs das eleições com periodo de candidatura fechada
    public Collection eleicoesFechadaCandidaturaID(){
        return this.eleicao.eleicoesFechadaCandidaturaID();
    }
    // Usada para obter IDs das eleições com periodo de candidatura fechada e validadas
    public Collection eleicoesProntaAVotoID(){
        return this.eleicao.eleicoesProntaAVotoID();
    }
    // Usada para obter IDs das eleições com recem-criada
    public Collection eleicoesRecemCriadasID(){
        return this.eleicao.eleicoesRecemCriadasID();
    }
    
    // Usada para fazer String no jList ADMIN
    public Collection eleicoesAdmin(){
        return this.eleicao.eleicoesAdmin();
    }
}
