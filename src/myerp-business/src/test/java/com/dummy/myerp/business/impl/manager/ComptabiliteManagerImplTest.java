package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.comptabilite.*;
import static org.assertj.core.api.Assertions.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class ComptabiliteManagerImplTest extends BusinessTestCase {

    @Autowired
    private DaoProxy daoProxy;

//    private ComptabiliteDao dao = getDaoProxy().getComptabiliteDao();

    @Autowired
    private ComptabiliteDao comptabiliteDao;


    @Mock
    private ComptabiliteManagerImpl comptabiliteManager;

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();

    @Autowired
    private BusinessProxy businessProxy;


    @Autowired
    private TransactionManager transactionManager;

    String expectedReference;

    EcritureComptable ecritureComptable = new EcritureComptable();
    @Before
    public void BeforeAllTest() throws ParseException {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setReference("AC-2020/00001");
    }

    @Test
    public void checkEcritureComptableUnit() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(ecritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        manager.checkEcritureComptableUnit(ecritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        manager.checkEcritureComptableUnit(ecritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        manager.checkEcritureComptableUnit(ecritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5() throws Exception {

        manager.checkEcritureComptableUnit(ecritureComptable);
    }
    @Test
    public void addReferenceTest() throws FunctionalException, ParseException {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setJournal(new JournalComptable("AC","Banque"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateJournal = sdf.format(new Date());
        Date date=new SimpleDateFormat("yyyy/MM/dd").parse(dateJournal);
        ecritureComptable.setDate(date);


        System.out.println(dateJournal);
        expectedReference = "AC-2020/00001";
//        List e = dao.getListEcritureComptable();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(ecritureComptable.getDate());
//        Integer annee = new Integer(calendar.get(Calendar.YEAR));
//        int dernierNombre = 1;
//
//        String sequence = new DecimalFormat("00000").format(dernierNombre);
//        ecritureComptable.setReference(ecritureComptable.getJournal().getCode()+"-"+annee+"/"+sequence);

        manager.addReference(ecritureComptable);

        assertThat(ecritureComptable.getReference()).isEqualTo(expectedReference);

        SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();
        sequenceEcritureComptable.setCodeJournal("AC");
        sequenceEcritureComptable.setDerniereValeur(2020);
        sequenceEcritureComptable.setDerniereValeur(00001);
        manager.deleteSequenceEcritureComptable(sequenceEcritureComptable);
    }

}
