package com.dummy.myerp.model.bean.comptabilite;


/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

    // ==================== Attributs ====================
    /** code journal */
    private String codeJournal;
    /** L'année */
    private Integer annee;
    /** La dernière valeur utilisée */
    private Integer derniereValeur;

    // ==================== Constructeurs ====================
    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee -
     * @param pDerniereValeur -
     */
    public SequenceEcritureComptable(String codeJournal,Integer pAnnee, Integer pDerniereValeur) {
        this.codeJournal=codeJournal;
        annee = pAnnee;
        derniereValeur = pDerniereValeur;
    }


    // ==================== Getters/Setters ====================

    public String getCodeJournal() { return codeJournal;  }
    public void setCodeJournal(String codeJournal) { this.codeJournal = codeJournal;  }
    public Integer getAnnee() {
        return annee;
    }
    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }
    public Integer getDerniereValeur() {
        return derniereValeur;
    }
    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        return "SequenceEcritureComptable{" +
                "codeJournal='" + codeJournal + '\'' +
                ", annee=" + annee +
                ", derniereValeur=" + derniereValeur +
                '}';
    }
}
