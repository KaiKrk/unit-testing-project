package com.dummy.myerp.comsumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;



public class ComptabiliteDaoImplITTest extends ConsumerTestCase {
    private ComptabiliteDao comptabiliteDao = getDaoProxy().getComptabiliteDao();


    @Test
    public void getListCompteComptableTest() {
        List<CompteComptable> compteComptableList = comptabiliteDao.getListCompteComptable();
        Assert.assertTrue(!compteComptableList.isEmpty());
    }

    @Test
    public void getListJournalComptableTest() {
        List<JournalComptable> journalComptableList = comptabiliteDao.getListJournalComptable();
        Assert.assertTrue(!journalComptableList.isEmpty());
    }

    @Test
    public void getListSequenceEcritureComptableTest(){
        List<SequenceEcritureComptable> sequenceEcritureComptableList = comptabiliteDao.getListSequenceEcritureComptable();
        Assert.assertTrue(!sequenceEcritureComptableList.isEmpty());
    }

    @Test
    public void getListEcritureComptableTest() {
        List<EcritureComptable> ecritureComptableList = comptabiliteDao.getListEcritureComptable();
        Assert.assertTrue(!ecritureComptableList.isEmpty());
    }

    @Test
    public void getEcritureComptableTest() throws NotFoundException {
        EcritureComptable vEcritureComptable = comptabiliteDao.getEcritureComptable(-3);
        Assert.assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

//        Assertions.assertThrows(NotFoundException.class, () -> comptabiliteDao.getEcritureComptable(0));
    }

    @Ignore
    @Test
    public void getEcritureComptableByRefTest() throws NotFoundException {
        EcritureComptable ecritureComptable = comptabiliteDao.getEcritureComptableByRef("BQ-2016/00003");
        Assert.assertEquals("BQ", ecritureComptable.getJournal().getCode());
        String annee = new SimpleDateFormat("yyyy").format(ecritureComptable.getDate());
        Assert.assertEquals(annee,"2016");
        Assert.assertEquals(ecritureComptable.getId().intValue(), -3);

//        Assertions.assertThrows(NotFoundException.class, ()-> comptabiliteDao.getEcritureComptableByRef("BQ-2016/33333"));
    }

    @Test
    public void loadListLigneEcritureTest() {
        EcritureComptable ecritureComptable  = new EcritureComptable();
        ecritureComptable.setId(-3);
        comptabiliteDao.loadListLigneEcriture(ecritureComptable);
        Assert.assertEquals(ecritureComptable.getListLigneEcriture().size(),2);
    }

    @Test
    public void insertEcritureComptableTest() {
        EcritureComptable ecritureComptable  = new EcritureComptable();
        Date currentDate = new Date();
        Integer annee = 2020;
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setReference(ecritureComptable.getJournal().getCode()+"-" + annee + "/00007");
        ecritureComptable.setDate(currentDate);
        ecritureComptable.setLibelle("Courses");

        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),"Achat", new BigDecimal(25),null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),"Vente", null ,new BigDecimal(50)));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),"Achat", new BigDecimal(25),null));

        comptabiliteDao.insertEcritureComptable(ecritureComptable);

        List<EcritureComptable> listEcritureComptable = comptabiliteDao.getListEcritureComptable();
        EcritureComptable ecritureComptable1 = new EcritureComptable();

        for(EcritureComptable tempEcriture : listEcritureComptable){
            if(tempEcriture.getReference().equals(ecritureComptable.getReference()) && tempEcriture.getLibelle().equals("Courses")){
                ecritureComptable1 = tempEcriture;
            }
        }
        Assert.assertTrue(ecritureComptable.getReference().equals(ecritureComptable1.getReference()));
        Assert.assertTrue(ecritureComptable.getLibelle().equals(ecritureComptable1.getLibelle()));
        comptabiliteDao.deleteEcritureComptable(ecritureComptable1.getId());

    }

    @Ignore
    @Test
    public void updateEcritureComptableTest() throws NotFoundException {
        EcritureComptable ecritureComptable  = new EcritureComptable();
        Date currentDate = new Date();
        Integer annee = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setReference("AC-" + annee + "/00066");
        ecritureComptable.setDate(currentDate);
        ecritureComptable.setLibelle("Depense");

        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),"Facture EDF", new BigDecimal(45),null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),"Fournitures", null,new BigDecimal(45)));

        comptabiliteDao.insertEcritureComptable(ecritureComptable);
        EcritureComptable ecritureComptableBis = comptabiliteDao.getEcritureComptableByRef("AC-" + annee + "/00066");

        ecritureComptableBis.setLibelle("Achat");

        comptabiliteDao.updateEcritureComptable(ecritureComptableBis);

        Assert.assertTrue(comptabiliteDao.getEcritureComptableByRef("AC-" + annee + "/00066").getLibelle().equals("Achat"));
        comptabiliteDao.deleteEcritureComptable(comptabiliteDao.getEcritureComptableByRef("AC-" + annee + "/00066").getId());
    }

    @Test(expected = NotFoundException.class)
    public void deleteEcritureComptableTest() throws NotFoundException{
        comptabiliteDao.deleteEcritureComptable(-80);
        comptabiliteDao.getEcritureComptable(-80);

    }


    @Test
    public void insertSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2020);
        sequence.setDerniereValeur(12);

        comptabiliteDao.insertSequenceEcritureComptable(sequence);
        List<SequenceEcritureComptable> vList = comptabiliteDao.getListSequenceEcritureComptable();
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        for (SequenceEcritureComptable seq :vList){
            if(seq.getCodeJournal().equals("AC") && seq.getAnnee()==2020){
                sequenceEcritureComptable=seq;
            }
        }
        Assert.assertEquals("AC", sequenceEcritureComptable.getCodeJournal());
        Assert.assertTrue(sequenceEcritureComptable.getAnnee()==2020);
        Assert.assertTrue(sequenceEcritureComptable.getDerniereValeur()==12);
        comptabiliteDao.deleteSequenceEcritureComptable(sequence);

    }

    @Test
    public void updateSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2032);
        sequence.setDerniereValeur(8);

        comptabiliteDao.insertSequenceEcritureComptable(sequence);
        sequence.setDerniereValeur(62);

        comptabiliteDao.updateSequenceEcritureComptable(sequence);

        List<SequenceEcritureComptable> sequenceEcritureComptableList = comptabiliteDao.getListSequenceEcritureComptable();
        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();

        for(SequenceEcritureComptable tempSequence : sequenceEcritureComptableList){
            if (tempSequence.getCodeJournal().equals("AC") && tempSequence.getAnnee().equals(2032)){
                sequenceEcritureComptable = tempSequence;
            }
        }

        Assert.assertTrue(sequenceEcritureComptable.getDerniereValeur().equals(62));

        comptabiliteDao.deleteSequenceEcritureComptable(sequence);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2020);
        sequence.setDerniereValeur(16);

        comptabiliteDao.insertSequenceEcritureComptable(sequence);
        List<SequenceEcritureComptable> listBeforeDelete = comptabiliteDao.getListSequenceEcritureComptable();
        comptabiliteDao.deleteSequenceEcritureComptable(sequence);
            sequence = comptabiliteDao.getListSequenceEcritureComptable().get(listBeforeDelete.size());
    }

}
